package com.company.network;

/**
 * Classe de enumuração que define os tipos de pedidos possíveis para o webservice.
 * 
 * @author pbs
 */
public enum HttpRequestType {
    /**
     * GET, permite obter dados do servidor.
     */
    GET,
    /**
     * POST, permite publicar dados no servidor. 
     */
    POST,
    /**
     * PUT, permite alterar dados no servidor.
     */
    PUT,
    /**
     * DELETE, permite apagar dados do servidor.
     */
    DELETE
}
