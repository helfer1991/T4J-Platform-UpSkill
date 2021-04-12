/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.dto;

import com.companyWS.domain.Context;
import com.companyWS.domain.Role;
import com.companyWS.domain.Session;
import com.companyWS.domain.User;

/**
 *
 * @author joaor
 */
public class Mapper {

    public static ContextDTO context2ContextDTO(Context context) {
        return new ContextDTO(context.getContext());
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static SessionDTO session2SessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        UserDTO userDTO = Mapper.user2UserDTO(session.getUser());
        sessionDTO.setUser(userDTO);
        sessionDTO.setLogindate(session.getDate().toString());

        return sessionDTO;
    }
    
    public static RoleDTO role2RoleDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole(role.getRole());
        roleDTO.setDescricao(role.getDescricao());
        
        return roleDTO;
    }
}
