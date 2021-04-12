/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import Persistence.FabricaRepositorios;
import com.company.controller.UsersAPI;

/**
 * Classe principal responsável por integrar uma API, uma fábrica de repositórios, e um algoritmo gerador de password's
 * @author Asus
 */
public class Plataforma {

    private static Plataforma plataforma;
    private UsersAPI uAPI;
    private IAlgoritmoGeradorPasswords agp;
    private FabricaRepositorios repo;


    /**
     * Inicialização da API e do Algoritmo gerador de password's.
     */
    public Plataforma(){
        //Geração de passwords
        agp = new AlgoritmoGeradorPasswords();
        //UsersAPI
        uAPI = new UsersAPI();

    }

    /**
     * Devolve a instância da plataforma para disponibilizar acesso aos seus
     * métodos.
     *
     * @return plataforma
     */
    public static Plataforma getInstance() {
        if (Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    /**
     * Devolve o objecto que vai gerar a password.
     *
     * @return objecto que vai gerar a password
     */
    public IAlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return agp;
    }

    /**
     * Devolve o objecto users api.
     *
     * @return objecto uAPI do tipo UsersAPI
     */
    public UsersAPI getUsersAPI() {
        return uAPI;
    }

    /**
     * Reinicia a aplicação que trata dos utilizadores
     */
    public void restartUsersAPI() {
        this.uAPI = new UsersAPI();
    }

}
