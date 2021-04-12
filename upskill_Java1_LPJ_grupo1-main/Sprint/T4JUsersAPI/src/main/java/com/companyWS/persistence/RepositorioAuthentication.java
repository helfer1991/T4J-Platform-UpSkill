/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.persistence;

import com.companyWS.domain.Context;
import com.companyWS.domain.Session;
import com.companyWS.domain.User;
import com.companyWS.domain.Data;
import com.companyWS.exception.FetchingException;
import com.companyWS.exception.NonAssociatedContextException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author joaor
 */
public class RepositorioAuthentication {

    private static RepositorioAuthentication instance;

    private ConnectionHandler connectionHandler;

    private RepositorioAuthentication() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Static method that returns a unique reference to the class object, that
     * implements a singleton.
     *
     * @return instance
     */
    public static RepositorioAuthentication getInstance() throws SQLException {
        if (instance == null) {
            instance = new RepositorioAuthentication();
        }
        return instance;
    }

    /**
     * Boolean method which checks if the Context was inserted.
     *
     * @param context
     * @return boolean
     */
    public boolean insertContext(Context context) {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into ContextKeys(key, estado) values (?, ?)");
            ResultSet rs = null;

            conn.setAutoCommit(false);

            pstmt.setString(1, context.getContext());
            pstmt.setString(2, "valid");

            pstmt.executeQuery();

            conn.commit();
            pstmt.close();

            return true;
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        }

        return false;

    }

    /**
     * Method which gets an Context by string.
     *
     * @param contextString
     * @return context
     */
    public Context getContextByString(String contextString) throws Exception {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ContextKeys where key = ?");
            pstmt.setString(1, contextString);

            Context context = montarContext(pstmt.executeQuery());

            pstmt.close();
            return context;
        } catch (SQLException e) {
            throw new NonAssociatedContextException(contextString + " Contexto Invalido");
        }
    }

    /**
     * Boolean method which checks if a Session was inserted.
     *
     * @param session
     * @return boolean
     */
    public void insertSession(Session session) throws SQLException {
        Connection conn = connectionHandler.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareCall("Select email from Utilizador where email = ?");
            pstmt.setString(1, session.getUser().getEmail().toString());
            rs = pstmt.executeQuery();
            rs.next();

            String idUtilizador = rs.getString(1);

            pstmt = conn.prepareCall("insert into Sessao(IDUTILIZADOR, CONTEXTKEY, DATALOGIN) values (?, ?, ?)");

            conn.setAutoCommit(false);

            pstmt.setString(1, idUtilizador);
            pstmt.setString(2, session.getContext().getContext());
            pstmt.setDate(3, session.getDate());

            pstmt.executeQuery();

            conn.commit();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            conn.setAutoCommit(true);
        }

    }

    /**
     * Boolean method which checks if a context is invalid.
     *
     * @param context
     * @return boolean
     */
    public void closeContext(String context) throws SQLException {
        Connection conn = connectionHandler.getConnection();
        CallableStatement csUpdateContext = null;
        try {
            conn.setAutoCommit(false);
            
            csUpdateContext = conn.prepareCall("{call closeContext(?)}");
            
            csUpdateContext.setString(1, context);

            csUpdateContext.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        } finally {
            if (csUpdateContext != null) {
                csUpdateContext.close();
            }
            conn.setAutoCommit(true);
        }

    }

    /**
     * Method which gets a Session by its Context.
     *
     * @param contextString
     * @return session
     */
    public Session getSessionByContext(String contextString) throws NonAssociatedContextException {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SESSAO where contextkey = ?");
            pstmt.setString(1, contextString);

            Session session = montarSession(pstmt.executeQuery());

            pstmt.close();
            return session;
        } catch (SQLException e) {
            throw new NonAssociatedContextException(contextString + " não está associado a nenhuma sessão");
        }
    }

    private Session montarSession(ResultSet rs) {
        Session session = null;

        try {
            Connection conn = connectionHandler.getConnection();

            while (rs.next()) {
                //Construir objeto User
                PreparedStatement pstmtUtilizador = conn.prepareStatement("SELECT * FROM Utilizador WHERE email = ?");
                pstmtUtilizador.setString(1, rs.getString("idutilizador"));
                ResultSet rSetUser = pstmtUtilizador.executeQuery();

                User user = RepositorioUser.getInstance().montarUtilizador(rSetUser);

                //Construir Context
                Context con = new Context(rs.getString("contextkey"), true);

                //Construir LoginDate
                Date data = rs.getDate("DATALOGIN");
                String[] dataString = data.toString().split("-");

                Data dataLogin = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1]),
                        Integer.parseInt(dataString[2]));

                session = new Session(user, con);
                pstmtUtilizador.close();
            }
            rs.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (session != null) {
            return session;
        } else {
            throw new FetchingException("Problema a montar sessão");
        }
    }

    private Context montarContext(ResultSet row) {
        Context context = null;
        try {
            row.next();
            String contextKey = row.getString(1);
            String estado = row.getString(2);
            boolean valido = estado.equalsIgnoreCase("valid");

            context = new Context(contextKey, valido);

            row.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (context != null) {
            return context;
        } else {
            throw new FetchingException("Problema a montar o context");
        }
    }
}
