/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 * Objecto Área de Atividade
 * @author Asus
 */
public class AreaAtividade {

    /**
     * Atribuição de um padrão de comparação com o id.
     */
    private static final String PATTERN_ID = "[0-9]+";

    /**
     * Identificação única da área de atividade.
     */
    private String id;

    /**
     * Descrição breve da área de atividade.
     */
    private String descBreve;

    /**
     * Descrição detalhada da área de atividade.
     */
    private String descDetalhada;

    /**
     * Constrói uma instância de Area de Atividade recebendo o identificador, a
     * descrição breve e a descrição detalhada. Só é criada se todos os
     * parâmetros forem válidos.
     *
     * @param id identificação da area de atividade
     * @param descBreve descrição breve da area de atividade
     * @param descDetalhada descrição detalhada da area de atividade
     */
    public AreaAtividade(String id, String descBreve, String descDetalhada) {
        if (validateData(id, descBreve, descDetalhada)) {
            setId(id);
            setDescBreve(descBreve);
            setDescDetalhada(descDetalhada);
        }
    }

    /**
     * Verifica se cada parâmetro de entrada é nulo ou vazio.
     *
     * @param id identificação da area de atividade
     * @param descBreve descrição breve da area de atividade
     * @param descDetalhada descrição detalhada da area de atividade
     * @return true se todos os parâmetros forem válidos, caso contrário devolve
     * false com uma mensagem de erro
     */
    private boolean validateData(String id, String descBreve, String descDetalhada) {
        if ((id == null) || (descBreve == null) || (descDetalhada == null) || (id.isEmpty()) || (descBreve.isEmpty()) || (descDetalhada.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        } else {
            return true;
        }
    }

    /**
     * Verifica se o id da área de atividade é igual ao id recebido por parêmetro.
     *
     * @param id identificação
     * @return true se o id da area de atividade for igual ao id da area de
     * atividade recebido por parâmetro caso contrário devolve false.
     */
    public boolean hasId(String id) {
        return this.id.equalsIgnoreCase(id);
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve o id da area de atividade.
     *
     * @return id da area de atividade
     */
    public String getId() {
        return this.id;
    }

    /**
     * Modifica o id, concatenando-o da forma "Aa-" + id. O id apenas assume o
     * novo formato se for numérico, se não o for apresenta uma mensagem de
     * erro.
     *
     * @param id identificação da area de atividade no padrão "[0-9]+"
     */
    private void setId(String id) {
        char a = 'A';

        if (id.charAt(0) == a) {
            this.id = id;
        } else {
            if (id.matches(PATTERN_ID)) {
                String aaId = "Aa-" + id;
                this.id = aaId;
            }else{
                 throw new IllegalArgumentException("Id deve ser do tipo numérico.");
            }
        }
    }

    /**
     * Devolve a descrição breve da área de atividade.
     *
     * @return descrição breve da áre de atividade
     */
    public String getDescBreve() {
        return descBreve;
    }

    /**
     * Modifica a descrição breve da área de atividade. Verifica se o novo
     * tamanho da descrição breve tem menos de 25 caracteres, caso contrário
     * apresenta mensagem de erro.
     *
     * @param descBreve nova descrição breve da area de atividade
     */
    private void setDescBreve(String descBreve) {
        if (descBreve.length() < 25) {
            this.descBreve = descBreve;
        } else {
            throw new IllegalArgumentException("Descrição Breve não deve ser maior do que 25 caracteres");
        }
    }

    /**
     * Devolve a descrição detalhada da área de atividade.
     *
     * @return descrição detalhada da área de atividade
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }

    /**
     * Modifica a descrição detalhada da área de atividade. Verifica se o novo
     * tamanho da descrição detalhada tem menos de 100 caracteres, caso
     * contrário apresenta mensagem de erro.
     *
     * @param descDetalhada nova descrição detalhada da area de atividade
     */
    private void setDescDetalhada(String descDetalhada) {
        if (descBreve.length() < 100) {
            this.descDetalhada = descDetalhada;
        } else {
            throw new IllegalArgumentException("Descrição Detalhada não deve ser maior do que 100 caracteres");
        }
    }
    
    // </editor-fold>

    /**
     * Devolve o hashCode do id da área de atividade.
     *
     * @return hashCode do id da área de atividade
     * @see Object#hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Compara a área de atividade com o objeto recebido.
     *
     * @param o objeto a comparar com a área de atividade.
     * @return true se o objeto recebido representar um objecto
     * equivalente à área de atividade. Caso contrário, retorna false.
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
        AreaAtividade obj = (AreaAtividade) o;
        //return (Objects.equals(id, obj.id));
        return this.id.equalsIgnoreCase(obj.getId()) && this.getDescBreve().equalsIgnoreCase(obj.getDescBreve());
    }

    /**
     * Devolve a descrição textual da área de atividade no formato: ID --
     * Descrição Breve -- Descrição Detalhada.
     *
     * @return caraterísticas da área de atividade
     */
    @Override
    public String toString() {
        return String.format("%s -- %s -- %s", this.id, this.getDescBreve(), this.getDescDetalhada());
    }

}
