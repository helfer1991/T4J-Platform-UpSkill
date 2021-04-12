/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.api;

import com.company.network.HttpConnection;
import com.company.network.HttpRequest;
import com.company.network.HttpRequestType;
import com.company.network.HttpResponse;
import com.company.network.HttpStatusCode;
import org.json.JSONObject;

/**
 * Classe responsável pela conecção e tratamento de pedidos entre a aplicação e
 * o webservice.
 *
 * @author joaor
 */
public class UsersAPIAdapter {

    private String appContext;
    private String appKey;

    /**
     * Constrói uma instância de Users API Adapter.
     *
     * @param appKey chave de autênticação
     */
    public UsersAPIAdapter(String appKey) {
        this.appKey = appKey;

    }

    /**
     * Devolve o contexto disponibilizado pelo webservice.
     *
     * @return contexto disponibilizado pelo webservice
     */
    public String getContext() {
        if (appContext == null || appContext.equals("")) {
            String url = "/context?app_key=" + appKey;
            HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
            HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
            switch (httpResponse.getStatus()) {
                case HttpStatusCode.OK:
                    break;
                case HttpStatusCode.Conflict:
                    break;
            }
            try {
                JSONObject bodyJSON = new JSONObject(httpResponse.getBody().replaceAll("\\[|\\]", ""));
                appContext = bodyJSON.getString("appContext");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //appContext = "{7E19F342-A903-4C3B-806A-CF771120B9D0}";
        }
        return appContext;
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
        String url = "/login?app_context=" + getContext() + "&user_id=" + user_id + "&password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    /**
     * Devolve true se o servidor permitir fazer logout, caso contrário devolve
     * false.
     *
     * @return true se o servidor permitir fazer logout, caso contrário devolve
     * false
     */
    public boolean logout() {
        String url = "/logout?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
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
    public boolean registerUser(String username, String email,String telefone, String password) {
        String url = "/registerUser?app_context=" + getContext() + "&username=" + username + "&email=" + email +
                "&telefone=" + telefone + "&password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
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
    public boolean registerUserWithRoles(String username, String email,String telefone, String password, String rolenames) {
        String url = "/registerUserWithRoles?app_context=" + getContext() + "&username=" + username.replace(" ", "!") + "&email=" + email
                + "&telefone=" + telefone + "&password=" + password + "&rolenames=" + rolenames;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    /**
     * Devolve a sessão.
     *
     * @return sessão
     */
    public String getSession() {
        String url = "/session?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                break;
            case HttpStatusCode.Conflict:
                break;
        }

        return httpResponse.getBody().replaceAll("\\[|\\]", "");
    }
}
