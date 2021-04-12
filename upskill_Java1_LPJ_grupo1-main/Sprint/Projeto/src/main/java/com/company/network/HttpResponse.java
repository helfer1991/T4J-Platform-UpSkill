package com.company.network;

/**
 * Classe com métodos para criar a resposta do webservice.
 * 
 * @author pbs
 */
public class HttpResponse {

    private int status;
    private String body;

    /**
     * Constrói uma instância de HttpResponse recebendo por parâmetros o estado e o corpo.
     * 
     * @param status estado da resposta do HttpResponse
     * @param body corpo da resposta do HttpResponse
     */
    public HttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    /**
     * Devolve o estado do HttpResponse.
     * 
     * @return estado do HttpResponse
     */
    public int getStatus() {
        return status;
    }

    /**
     * Atribui um novo estado ao HttpResponse.
     * 
     * @param status um novo estado do HttpResponse
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Devolve o corpo do HttpResponse.
     * 
     * @return corpo do HttpResponse
     */
    public String getBody() {
        return body;
    }

    /**
     * Atribui um novo corpo do HttpResponse.
     * 
     * @param body um novo corpo do HttpResponse
     */
    public void setBody(String body) {
        this.body = body;
    }
}
