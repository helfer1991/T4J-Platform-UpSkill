/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.persistence;

import com.companyWS.domain.Context;
import com.companyWS.domain.Email;
import com.companyWS.domain.Role;
import com.companyWS.domain.Session;
import com.companyWS.domain.User;
import com.companyWS.exception.FetchingException;
import com.companyWS.exception.NonAssociatedEmailException;
import com.companyWS.exception.NonAssociatedName;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaor
 */
public class RepositorioUser {

    private static RepositorioUser instance;

    private ConnectionHandler connectionHandler;

    private RepositorioUser() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Static method that returns a unique reference to the class object, that
     * implements a singleton.
     *
     * @return instance
     */
    public static RepositorioUser getInstance() throws SQLException {
        if (instance == null) {
            instance = new RepositorioUser();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a user exists in the repository, otherwise
     * it is added to it.
     *
     * @param user
     * @return boolean
     */
    public void save(User user) {
        Connection conn = connectionHandler.getConnection();
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{CALL addUtilizador(?,?,?,?,?)}");
            ResultSet rs = null;

            conn.setAutoCommit(false);

            cs.setString(1, user.getEmail().toString());
            cs.setString(2, user.getUsername());
            cs.setInt(3, Integer.valueOf(user.getTelefone()));
            if (user.getRole() != null) {
                cs.setString(4, user.getRole().getRole());
            }else{
                cs.setString(4, null);
            }
            cs.setString(5, user.getPassword());

            cs.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

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
    }

    /**
     * Method to get a user through your email.
     *
     * @param email
     * @return u
     */
    public User getUtilizadorByEmail(Email email) {
        try {
            Connection conn = connectionHandler.getConnection();
            String emailUtilizador = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utilizador where email = ?");
            pstmt.setString(1, emailUtilizador);

            User user = montarUtilizador(pstmt.executeQuery());

            pstmt.close();
            return user;
        } catch (SQLException e) {
            throw new NonAssociatedEmailException(email.toString() + " não está associado a nenhum utilizador");
        }
    }

    /**
     * Method for obtaining a user using his username.
     *
     * @param nome
     * @return u
     */
    public User getUtilizadorByNome(String nome) {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utilizador where nome = ?");
            pstmt.setString(1, nome);

            User user = montarUtilizador(pstmt.executeQuery());

            pstmt.close();
            return user;
        } catch (SQLException e) {
            throw new NonAssociatedName(nome + " não está associado a nenhum utilizador");
        }
    }

    public User montarUtilizador(ResultSet rs) {

        User user = null;

        try {
            Connection conn = connectionHandler.getConnection();

            //Parâmetros do User disponiveis
            while (rs.next()) {
                String email = rs.getString("email");
                Email emailobj = new Email(email);
                String nome = rs.getString("nome");
                String telefone = String.valueOf(rs.getInt("telefone"));
                String password = rs.getString("password");

                //Construir objeto role
                PreparedStatement pstmtRole = conn.prepareStatement("SELECT * FROM Role WHERE role = ?");
                pstmtRole.setString(1, rs.getString("role"));
                ResultSet rs1 = pstmtRole.executeQuery();
                rs1.next();
                Role role = new Role(rs1.getString("role"), rs1.getString("descricao"));

                user = new User(nome, password, telefone, emailobj, role);
            }

            rs.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        } finally {
            if (user != null) {
                return user;
            } else {
                throw new FetchingException("Problema em montarUtilizador");
            }
        }
    }

    public void deleteRoleFromUser(User user) {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("update utilizador set role = null where email = ?");
            pstmt.setString(1, user.getEmail().toString());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            throw new NonAssociatedName(user.getEmail() + " não está associado a nenhum utilizador");
        }
    }

    public Session getSession(String appContext) {
       try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SESSAO where contextkey = ?");
            pstmt.setString(1, appContext);
            
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            User user = getUtilizadorByEmail(new Email(rs.getString(1)));
            Context context = new Context(appContext,true);
            
            
            
            Session session = new Session(user, context, rs.getDate(3));
            
            pstmt.close();
            return session;
        } catch (SQLException e) {
            throw new NonAssociatedName(appContext + " não está associada a nenhuma sessao");
        }
    }
}
