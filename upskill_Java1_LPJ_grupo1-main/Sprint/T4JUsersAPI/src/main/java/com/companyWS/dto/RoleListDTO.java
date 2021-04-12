/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.dto;

import java.util.ArrayList;

/**
 *
 * @author joaor
 */
public class RoleListDTO {

    private ArrayList<RoleDTO> roles;

    public RoleListDTO() {
        roles = new ArrayList<>();
    }

    public ArrayList<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<RoleDTO> roles) {
        this.roles = roles;
    }
    
    public void addRole (RoleDTO role){
        this.roles.add(role);
    }

}
