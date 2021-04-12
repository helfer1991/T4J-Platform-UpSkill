/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.domain;

/**
 *
 * @author joaor
 */
public class Role {

    private String role;
    private String descricao;

    public Role(String role,String descricao) {
        this.role = role;
        this.descricao = descricao;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return getDescricao().equals(role.getDescricao()) && getRole().equals(role.getRole());
    }

    @Override
    public String toString() {
        return "Role{"
                + "descricao='" + descricao + '\''
                + ", role ='" + role + '\''
                + '}';
    }
}
