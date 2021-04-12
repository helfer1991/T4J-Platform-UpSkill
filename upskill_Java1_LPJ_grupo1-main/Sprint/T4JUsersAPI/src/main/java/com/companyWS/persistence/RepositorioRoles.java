/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.persistence;

import com.companyWS.domain.Role;
import com.companyWS.domain.User;
import com.companyWS.exception.FetchingException;
import com.companyWS.exception.NonAssociatedName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaor
 */
public class RepositorioRoles {

    private static RepositorioRoles instance;

    private ConnectionHandler connectionHandler;

    private RepositorioRoles() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Static method that returns a unique reference to the class object, that
     * implements a singleton.
     *
     * @return instance
     */
    public static RepositorioRoles getInstance() throws SQLException {
        if (instance == null) {
            instance = new RepositorioRoles();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a role exists in the repository, otherwise
     * it is added to it.
     *
     * @param role
     * @return boolean
     */
    public void save(Role role) throws SQLException {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into Role(role, descricao) values (?, ?)");
            ResultSet rs = null;

            conn.setAutoCommit(false);

            pstmt.setString(1, role.getRole());
            pstmt.setString(2, role.getDescricao());

            pstmt.executeQuery();

            conn.commit();
            pstmt.close();

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
            conn.setAutoCommit(true);
        }
    }

    /**
     * Method for obtaining a role through their user.
     *
     * @param nome
     * @return usersByRole
     */
    public Role getRoleByUtilizador(String nome) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            Connection conn = connectionHandler.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM Utilizador where nome = ?");
            pstmt.setString(1, nome);
            ResultSet rSetUtilizador = pstmt.executeQuery();
            rSetUtilizador.next();
            String role = rSetUtilizador.getString("role");

            pstmt = conn.prepareStatement("SELECT * FROM Role WHERE role = ?");
            pstmt.setString(1, role);
            ResultSet rSetRole = pstmt.executeQuery();
            rSetRole.next();

            Role rolez = montarRole(rSetRole);

            return rolez;
        } catch (SQLException e) {
            throw new NonAssociatedName(nome + " não está associado a nenhum utilizador");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public ArrayList<Role> getRoles() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        Connection conn = connectionHandler.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pstmtRoles = conn.prepareStatement("SELECT * FROM Role");
            rs = pstmtRoles.executeQuery();
            while (rs.next()) {
                roles.add(montarRole(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return roles;
    }

    public Role montarRole(ResultSet row) {

        Role role = null;
        try {
            String roles = row.getString("role");
            String descricao = row.getString("descricao");

            role = new Role(roles, descricao);

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (role != null) {
            return role;
        } else {
            throw new FetchingException("Erro montarRole");
        }

    }

    public Role getRole(String rolename) throws SQLException {
        PreparedStatement pstmt = null;
        Role role = null;
        try {
            Connection conn = connectionHandler.getConnection();

            pstmt = conn.prepareStatement("SELECT * FROM Role WHERE role = ?");
            pstmt.setString(1, rolename);
            ResultSet rSetRole = pstmt.executeQuery();
            rSetRole.next();

            role = montarRole(rSetRole);

            return role;
        } catch (SQLException e) {
            throw new NonAssociatedName(role + " não está associado a nenhum utilizador");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void deleteRole(Role role) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            Connection conn = connectionHandler.getConnection();

            pstmt = conn.prepareStatement("DELETE from ROLE where role = ? ");
            pstmt.setString(1, role.getRole());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new NonAssociatedName(role + " Não Consegue apagar roles que não existem");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

}
