/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.dto;

/**
 *
 * @author joaor
 */
public class ErroDTO {

    private String mensagemErro;

    public ErroDTO(Exception e) {
        mensagemErro = e.getMessage();
        e.printStackTrace();
    }

    public ErroDTO() {
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
