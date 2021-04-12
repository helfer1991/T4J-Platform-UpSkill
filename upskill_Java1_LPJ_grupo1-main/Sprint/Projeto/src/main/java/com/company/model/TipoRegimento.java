/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

/**
 * Objecto Tipo de Regimento
 * @author diana
 */
public class TipoRegimento {

    private String designacao;
    private String descricaoRegras;
    private int id;

    /**
     * Constrói uma instância de TipoRegimento recebendo os atributos por
     * parâmetro. Só é criada se todos os parâmetros forem válidos.
     *
     * @param designacao designação do tipo de regimento
     * @param descricaoRegras descrição das regras do tipo de regimento
     */
    public TipoRegimento(String designacao, String descricaoRegras) {
        if ((designacao == null) || (descricaoRegras == null) || (designacao.isEmpty()) || (descricaoRegras.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio");
        }
        setDesignacao(designacao);
        setDescricaoRegras(descricaoRegras);

    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve a designação do tipo de regimento.
     *
     * @return designação do tipo de regimento
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica a designação do tipo de regimento.
     *
     * @param designacao nova designação do tipo de regimento
     */
    private void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Devolve a descrição das regras do tipo de regimento.
     *
     * @return descrição das regras do tipo de regimento
     */
    public String getDescricaoRegras() {
        return descricaoRegras;
    }

    /**
     * Modifica a descrição das regras do tipo de regimento.
     *
     * @param descricaoRegras nova descrição das regras do tipo de regimento
     */
    private void setDescricaoRegras(String descricaoRegras) {
        this.descricaoRegras = descricaoRegras;
    }

    /**
     * Devolve a identificação do tipo de regimento.
     * @return identificação do tipo de regimento
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica a identificação do tipo de regimento.
     * @param id nova identificação do tipo de regimento
     */
    public void setId(int id) {
        this.id = id;
    }
    // </editor-fold>

    /**
     * Compara o TipoRegimento com o objeto recebido.
     *
     * @param o objeto a comparar com o TipoRegimento.
     * @return true se o objeto recebido representar um TipoRegimento
     * equivalente ao TipoRegimento. Caso contrário, retorna false.
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
        TipoRegimento obj = (TipoRegimento) o;

        return this.getDesignacao().equalsIgnoreCase(getDesignacao())
                && this.getDescricaoRegras().equalsIgnoreCase(getDescricaoRegras());
    }

    /**
     * Devolve a descrição textual do tipo de regimento no formato: Designação:
     * \n Descrição das regras: \n
     *
     * @return caraterísticas do tipo de regimento
     */
    @Override
    public String toString() {
        return String.format("Designação: %s\nDescrição das regras: %s",
                this.getDesignacao(), this.getDescricaoRegras());

    }
}
