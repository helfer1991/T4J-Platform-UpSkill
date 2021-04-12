/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.model.Plataforma;
import com.company.utils.Constantes;

/**
 * Classe que disponibiliza métodos para fazer logout.
 *
 * @author joaor
 */
public class MainSceneController {

    /**
     * Declaração da plataforma.
     */
    private Plataforma plat;
    private String role;

    /**
     * Constrói uma instância de MainSceneController. Inicializa a plataforma
     * com uma cópia da mesma.
     */
    public MainSceneController() {
        plat = Plataforma.getInstance();
        role = plat.getInstance().getUsersAPI().getRole();
    }

    /**
     * Devolve true se o servidor permitir fazer logout, caso contrário devolve
     * false.
     *
     * @return true se o servidor permitir fazer logout, caso contrário devolve
     * false
     */
    public boolean logout() {
        return plat.getUsersAPI().logout();
    }

    /**
     * Reinicia a aplicação que trata dos utilizadores
     */
    public void restartUAPI() {
        plat.restartUsersAPI();
    }

    public String getRole() {
        return role;
    }

    public boolean isColaborador() {
        return getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO);
    }

}
