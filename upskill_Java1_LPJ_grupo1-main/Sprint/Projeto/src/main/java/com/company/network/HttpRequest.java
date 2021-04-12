package com.company.network;

import com.company.utils.Constantes;

/**
 * Classe com métodos para criar o pedido ao webservice.
 * 
 * @author pbs
 */
public class HttpRequest {

    private HttpRequestType type;
    private String url;

    /**
     * Constrói uma instância de HttpResponse recebendo por parâmetros o tipo e o url.
     * @param type tipo do pedido (GET, POST, PUT, DELETE) ao servidor
     * @param url caminho da ligação ao servidor
     */
    public HttpRequest(HttpRequestType type, String url) {
        this.type = type;
        this.url = Constantes.HOST + url;
    }

    /**
     * Devolve o tipo do pedido.
     * 
     * @return tipo de pedido (GET, POST, PUT, DELETE)
     */
    public HttpRequestType getType() {
        return type;
    }

    /**
     * Define o novo tipo de pedido.
     * 
     * @param type novo tipo de pedido
     */
    public void setType(HttpRequestType type) {
        this.type = type;
    }

    /**
     * Devolve o caminho da ligação ao servidor.
     * 
     * @return caminho da ligação ao servidor
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define um novo caminho da ligação ao servidor.
     * 
     * @param url novo caminho da ligação ao servidor
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
