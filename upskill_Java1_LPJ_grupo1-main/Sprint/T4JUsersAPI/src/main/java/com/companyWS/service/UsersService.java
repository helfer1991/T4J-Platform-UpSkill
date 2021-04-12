/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.service;

import com.companyWS.domain.Email;
import com.companyWS.domain.Role;
import com.companyWS.domain.User;
import com.companyWS.dto.ContextDTO;
import com.companyWS.dto.Mapper;
import com.companyWS.dto.RoleDTO;
import com.companyWS.dto.UserDTO;
import com.companyWS.dto.RoleListDTO;
import com.companyWS.dto.SessionDTO;
import com.companyWS.exception.ConversionException;
import com.companyWS.persistence.RepositorioRoles;
import com.companyWS.persistence.RepositorioUser;
import java.sql.SQLException;

/**
 *
 * @author joaor
 */
public class UsersService {
    

    public static UserDTO getUtilizador(Email email) throws SQLException {
        RepositorioUser repo = RepositorioUser.getInstance();
        User user = repo.getUtilizadorByEmail(email);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = Mapper.user2UserDTO(user);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ConversionException("UserDTO");
        }
    }

    public static void registerUser(UserDTO userDTO) throws SQLException {
        User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getTelefone());
        if (user != null) {
            RepositorioUser repo = RepositorioUser.getInstance();
            repo.save(user);
        } else {
            throw new ConversionException("UserDTO");
        }
    }

    public static void registerUserWithRoles(UserDTO userDTO) throws SQLException {
        User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getTelefone(), userDTO.getEmail(), userDTO.getRole());
        if (user != null) {
            RepositorioUser repo = RepositorioUser.getInstance();
            repo.save(user);
        } else {
            throw new ConversionException("UserDTO");
        }
    }

    public static RoleListDTO getRoles() throws SQLException {
        RoleListDTO roles = new RoleListDTO();
        for (Role role : RepositorioRoles.getInstance().getRoles()) {
            RoleDTO roleDTO = Mapper.role2RoleDTO(role);
            roles.addRole(roleDTO);
        }
        return roles;
    }

    public static RoleDTO getUserRoles(String email) throws SQLException {

        RepositorioRoles repoRole = RepositorioRoles.getInstance();
        RepositorioUser repo = RepositorioUser.getInstance();

        Role role = repoRole.getRoleByUtilizador(repo.getUtilizadorByEmail(new Email(email)).getUsername());
        if (role == null) {
            return null;
        }
        RoleDTO roleDTO = Mapper.role2RoleDTO(role);
        if (roleDTO != null) {
            return roleDTO;
        } else {
            throw new ConversionException("RoleDTO");
        }

    }

    public static void addRoleToUser(User user, Role role) throws SQLException {
        user.setRole(role);
        if (user != null) {
            RepositorioUser repo = RepositorioUser.getInstance();
            repo.save(user);
        } else {
            throw new ConversionException("UserDTO");
        }

    }

    public static void createUserRole(RoleDTO roleDTO)  throws SQLException {
        RepositorioRoles repoRole = RepositorioRoles.getInstance();
        Role role = new Role(roleDTO.getRole(), roleDTO.getDescricao());
        if(role!=null){
            repoRole.save(role);
        }else{
             throw new ConversionException("roleDTO");
        }
    }

    public static void deleteUserRole(String rolename) throws SQLException {
        RepositorioRoles repoRole = RepositorioRoles.getInstance();
        repoRole.deleteRole(repoRole.getRole(rolename));
        
    }

    public static void deleteRoleFromUser(String email, String rolename) throws SQLException {
        RepositorioUser repo = RepositorioUser.getInstance();
        User user = repo.getUtilizadorByEmail(new Email(email));
        if (user.getRole() == null) {
            repo.deleteRoleFromUser(user);
        } else {
            throw new IllegalArgumentException("Este utilizador não tem role");
        }
    }

    public static SessionDTO getSession(ContextDTO context) throws SQLException {
        RepositorioUser repo = RepositorioUser.getInstance();
        return Mapper.session2SessionDTO(repo.getSession(context.getAppContext()));
        
    }
}
