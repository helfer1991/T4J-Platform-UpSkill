/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 * Objecto Grau de Proficiência
 * @author joaor
 */
public class GrauProficiencia {

    /**
     * Valor único da grau de proficiência.
     */
    private String valor;
    
    /**
     * Designação deste grau de proficiencia.
     */
    private String designacao;
    
     /**
     * Declaração do objeto Competencia Tecnica a que este grau pertence.
     */
    private CompetenciaTecnica compT;
    
    /**
     * Constrói uma instância de Grau de Proficiência recebendo os atributos por parâmetro. 
     * Só é criada se todos os parâmetros forem válidos.
     * @param valor valor do grau de proficiência
     * @param designacao designação do grau de proficiência
     * @param compT competência técnica do grau de proficiência
     */
    public GrauProficiencia(String valor, String designacao, CompetenciaTecnica compT) {
        if ((valor == null) || (designacao == null)
                || (compT == null) || (valor.isEmpty()) || (designacao.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }

        setValor(valor);
        setDesignacao(designacao);
        setCompT(compT);
    }
    
    
    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">

    /**
     * Devolve o valor do grau de proficiência
     * @return valor do grau de proficiência
     */
    public String getValor() {
        return valor;
    }

    private void setValor(String valor) {
       this.valor = valor;
        if (valor.length() < 10) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("valor não deve ser maior do que 10 caracteres");
        }
    }
    
    /**
     * Devolve a competência técnica do grau de proficiência.
     * @return competência técnica do grau de proficiência
     */
    public CompetenciaTecnica getCompT() {
        return compT;
    }

    private void setCompT(CompetenciaTecnica compT) {
       this.compT = compT;
    }
    
    /**
     * Devolve a designação do grau de proficiência.
     * @return designação do grau de proficiência
     */
    public String getDesignacao() {
        return designacao;
    }
    
    private void setDesignacao(String designacao) {
        this.designacao = designacao;
        if (designacao.length() < 100) {
            this.designacao = designacao;
        } else {
            throw new IllegalArgumentException("Designacao não deve ser maior do que 100 caracteres");
        }
    }  
    // </editor-fold>
    
     /**
     * Compara o Grau de Proficiência com o objeto recebido.
     *
     * @param o objeto a comparar com o grau de proficiência.
     * @return true se o objeto recebido representar um objecto
     * equivalente ao grau de proficiência. Caso contrário, retorna false.
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
        GrauProficiencia obj = (GrauProficiencia) o;
        return (Objects.equals(getValor(), obj.getValor()) && Objects.equals(getCompT().getId(), obj.getCompT().getId()));
    }
    
     /**
     * Devolve a descrição textual do Grau de Proficiência no formato: Valor -
     * Designaçã - CompetenciaTecnica:
     * compT.toString().
     *
     * @return caraterísticas do Grau Proficiencia
     */
    @Override
    public String toString() {
        return String.format("%s - %s  - Competência Técnica: %s", this.getValor(), this.getDesignacao(), this.getCompT().toString());
    }

    

}
