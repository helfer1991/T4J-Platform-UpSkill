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
public class ContextDTO {

    private String appContext;

    public ContextDTO(String appContext) {
        this.appContext = appContext;
    };

    public void setAppContext(String appContext) {
        this.appContext = appContext;
    }

    public String getAppContext() {
        return this.appContext;
    }
}
