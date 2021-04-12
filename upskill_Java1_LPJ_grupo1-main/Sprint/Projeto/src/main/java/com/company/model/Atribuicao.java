/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Objecto Atribuição
 *
 * @author joaor
 */
public class Atribuicao {

    private String id;
    private Classificacao pickedClass;
    private Date dataAtribuicao;

    private static int counter = 0;

    /**
     * Constrói uma instância de Atribuicao recebendo uma classificação por
     * parâmetro.
     *
     * @param pickedClass classificação escolhida
     */
    public Atribuicao(Classificacao pickedClass) {
        Date now = Date.valueOf(LocalDate.now());
        setId(now.getYear()+1900 + "-" + (++counter));
        setPickedClass(pickedClass);
        setDataAtribuicao(now);
    }

    /**
     * Constrói uma instância de Atribuição recebendo um id, uma classificação e uma data por parâmetro.
     * 
     * @param id identificação da atribuição
     * @param pickedClass classificação escolhida
     * @param dataAtribuicao data da atribuição
     */
    public Atribuicao(String id, Classificacao pickedClass, Date dataAtribuicao) {
        setId(id);
        setPickedClass(pickedClass);
        setDataAtribuicao(dataAtribuicao);
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    /**
     * Devolve a identificação da atribuição
     *
     * @return identificação da atribuição
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica a identificação da atribuição.
     *
     * @param id nova identificação da atribuição
     */
    private void setId(String id) {
        this.id = id;
    }

    /**
     * Devolve a classificação escolhida.
     *
     * @return classificação escolhida
     */
    public Classificacao getPickedClass() {
        return pickedClass;
    }

    /**
     * Modifica a classificação escolhida.
     *
     * @param pickedClass nova classificação escolhida
     */
    private void setPickedClass(Classificacao pickedClass) {
        this.pickedClass = pickedClass;
    }

    /**
     * Devolve a data de atribuição.
     *
     * @return data de atribuição
     */
    public Date getDataAtribuicao() {
        return dataAtribuicao;
    }

    /**
     * Modifica a data de atribuição
     *
     * @param dataAtribuicao nova data de atribuição
     */
    private void setDataAtribuicao(Date dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }
    // </editor-fold>

    /**
     * Compara o objecto Atribuicao com o objeto recebido.
     *
     * @param o objeto a comparar com a Atribuicao.
     * @return true se o objeto recebido representar um objecto equivalente à
     * Atribuicao. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        Atribuicao obj = (Atribuicao) o;
        return (Objects.equals(id, obj.getId()) && Objects.equals(pickedClass, obj.getPickedClass()));
    }

    /**
     * Devolve a descrição textual da Atribuicao no formato: Id - Classificação
     * - Data de atribuição.
     *
     * @return caraterísticas da Atribuição
     */
    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", this.getId(), this.getPickedClass(), this.getDataAtribuicao());
    }

}
