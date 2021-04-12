/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

/**
 * Classe de enumeração dos tipos de regimento
 * @author joaor
 */
public enum TiposRegimentoEnum {
    Tipo1(1), Tipo2(2), Tipo3(3);

    public final int id;

    private TiposRegimentoEnum(int id) {
        this.id = id;
    }
}
