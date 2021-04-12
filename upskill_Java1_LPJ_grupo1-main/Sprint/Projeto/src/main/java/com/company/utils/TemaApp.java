/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.utils;

/**
 *
 * @author diana
 */
public class TemaApp {
    private boolean tema;
    private static TemaApp temaapp;

    public static TemaApp getInstance(){
        if (TemaApp.temaapp == null) {
            TemaApp.temaapp = new TemaApp();
        }
        return temaapp;
    }

    public void disableTema() {
        this.tema = false;
    }

    public void enableTema() {
        this.tema = true;
    }

    public boolean isEnable() {
        if (this.tema == true) {
            return true;
        } else {
            return false;
        }
    }
    
}
