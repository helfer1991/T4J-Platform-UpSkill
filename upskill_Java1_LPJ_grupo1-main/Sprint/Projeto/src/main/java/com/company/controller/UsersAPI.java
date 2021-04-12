/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.api.UsersAPIAdapter;
import org.json.JSONObject;

/**
 *
 * @author joaor
 */
public class UsersAPI {

    /**
     * Declaração de um objecto do tipo UsersAPIAdapter.
     */
    UsersAPIAdapter uApiA;

    /**
     * Constrói uma instância de UserAPI com uma chave de autenticação pré
     * definida.
     */
    public UsersAPI() {
        String appkey = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";
        uApiA = new UsersAPIAdapter(appkey);
    }

    /**
     * Verifica se o user_id e a password correspondem às credenciais de
     * autenticação do webservice, e faz login.
     *
     * @param user_id id do utilizador
     * @param password password do utilizador
     * @return true se o user_id e a password do webservice forem iguais ao
     * user_id e password da aplicação recebidos por parâmetro, caso contrário
     * devolve false.
     */
    public boolean login(String user_id, String password) {
        return uApiA.login(user_id, password);
    }

    /**
     * Devolve true se o servidor permitir fazer logout, caso contrário devolve
     * false.
     *
     * @return true se o servidor permitir fazer logout, caso contrário devolve
     * false
     */
    public boolean logout() {
        return uApiA.logout();
    }

    /**
     * Devolve o contexto disponibilizado pelo webservice.
     *
     * @return contexto disponibilizado pelo webservice
     */
    public String getContext() {
        return uApiA.getContext();
    }

    /**
     * Verifica se pode registar um utilizador com os parâmetros de entrada,
     * nome de utilizador, e-mail, password e função. Se for possível regista-o.
     *
     * @param username nome de utilizador
     * @param email e-mail de utilizador
     * @param password password do utilizador
     * @param rolenames função do utilizador
     * @return true se o username, email, password e função no webservice forem
     * diferentes dos recebidos por parâmetro, caso contrário devolve false.
     */
    public boolean registerUserWithRoles(String username, String email, String telefone, String password, String rolenames) {
        return uApiA.registerUserWithRoles(username, email, telefone, password, rolenames);
    }

    /**
     * Verifica se pode registar um utilizador com os parâmetros de entrada,
     * nome de utilizador, e-mail e password. Se for possível regista-o.
     *
     * @param username nome de utilizador
     * @param email e-mail de utilizador
     * @param password password do utilizador
     * @return true se o username, email e password no webservice forem
     * diferentes dos recebidos por parâmetro, caso contrário devolve false.
     */
    public boolean registerUser(String username, String email, String telefone, String password) {
        return uApiA.registerUser(username, email, telefone, password);
    }

    /**
     * Devolve o e-mail do utilizador da sessão em curso.
     *
     * @return e-mail do utilizador da sessão em curso
     */
    public String getEmail() {
        String session = uApiA.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getJSONObject("user").getJSONObject("email").getString("email");
    }

    /**
     * Devolve a função do utilizador da sessão em curso.
     *
     * @return função do utilizador da sessão em curso
     */
    public String getRole() {
        String session = uApiA.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getJSONObject("user").getJSONObject("role").getString("role");
    }

}
