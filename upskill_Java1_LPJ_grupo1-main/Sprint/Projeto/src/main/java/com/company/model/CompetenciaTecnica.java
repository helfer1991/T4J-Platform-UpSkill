/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Objecto Competência Técnica
 * @author Asus
 */
public class CompetenciaTecnica {

    /**
     * Atribuição de um padrão de comparação com o id.
     */
    private static final String PATTERN_ID = "[0-9]+";

    /**
     * Identificação única da competência técnica.
     */
    private String id;

    /**
     * Descrição breve da competência técnica.
     */
    private String descBreve;

    /**
     * Descrição detalhada da competência técnica.
     */
    private String descDetalhada;

    /**
     * Declaração do objeto área de atividade.
     */
    private AreaAtividade areaAtividade;

    private ArrayList<GrauProficiencia> grausProficiencia;

    /**
     * Constrói uma instância de Competência Técnica recebendo o identificador,
     * a descrição breve, a descrição detalhada e a área de atividade. Só é
     * criada se todos os parâmetros forem válidos.
     *
     * @param id identificação da competência técnica
     * @param descBreve descrição breve da competência técnica
     * @param descDetalhada descrição detalhada da competência técnica
     * @param areaAtividade área de atividade em que se vai enquadrar a
     * Competência Técnica
     */
    public CompetenciaTecnica(String id, String descBreve, String descDetalhada, AreaAtividade areaAtividade) {
        if ((id == null) || (descBreve == null) || (descDetalhada == null)
                || (areaAtividade == null) || (id.isEmpty()) || (descBreve.isEmpty()) || (descDetalhada.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }

        setId(id);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
        setAreaAtividade(areaAtividade);
        grausProficiencia = new ArrayList<>();
    }

    /**
     * Adiciona um grau de proficiência à lista.
     * @param gp grau de proficiência
     */
    public void addGrauProficiencia(GrauProficiencia gp) {
        this.grausProficiencia.add(gp);
    }
    
     /**
     * Devolve uma lista de designações e valores, de graus de proficiência.
     * @return lista de graus de proficiência
     */
    public ArrayList<String> getListaGrausProficienciaString(){
        ArrayList<String> lista = new ArrayList<String>();
        for (GrauProficiencia grau : grausProficiencia) {
            lista.add(grau.getValor() + " : " + grau.getDesignacao());
        }
        return lista;
    }
    
    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    /**
     * Devolve o id da competência técnica.
     *
     * @return id da competência técnica
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica o id, concatenando-o da forma "CompTec-" + id. O id apenas
     * assume o novo formato se for numérico, se não o for apresenta uma
     * mensagem de erro.
     *
     * @param id identificação da competência técnica no padrão "[0-9]+"
     */
    private void setId(String id) {
        char c = 'C';
        if (id.charAt(0) == c) {
            this.id = id;
        } else {
            if (id.matches(PATTERN_ID)) {
                String aaId = "CompTec-" + id;
                this.id = aaId;
            } else {
                throw new IllegalArgumentException("ID deve ser numérico.");
            }
        }
    }

    /**
     * Modifica a lista de graus de proficiência.
     * @param listaGP nova lista de graus de proficiência
     */
    public void setGrausProficiencia(List<GrauProficiencia> listaGP) {
        this.grausProficiencia.addAll(listaGP);
    }

    /**
     * Devolve a descrição breve da competência técnica
     *
     * @return descrição breve da competência técnica
     */
    public String getDescBreve() {
        return descBreve;
    }

    /**
     * Modifica a descrição breve da competência técnica. Verifica se o novo
     * tamanho da descrição breve tem menos de 25 caracteres, caso contrário
     * apresenta mensagem de erro.
     *
     * @param descBreve nova descrição breve da competência técnica
     */
    private void setDescBreve(String descBreve) {
        if (descBreve.length() < 25) {
            this.descBreve = descBreve;
        } else {
            throw new IllegalArgumentException("Descrição breve não deve ser maior do que 25 caracteres");
        }
    }

    /**
     * Devolve a descrição detalhada da competência técnica.
     *
     * @return descrição detalhada da competência técnica
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }

    /**
     * Modifica a descrição detalhada da competência técnica. Verifica se o novo
     * tamanho da descrição detalhada tem menos de 100 caracteres, caso
     * contrário apresenta mensagem de erro.
     *
     * @param descDetalhada nova descrição detalhada da competência técnica
     */
    private void setDescDetalhada(String descDetalhada) {
        this.descDetalhada = descDetalhada;
        if (descBreve.length() < 100) {
            this.descDetalhada = descDetalhada;
        } else {
            throw new IllegalArgumentException("Descrição detalhada não deve ser maior do que 100 caracteres");
        }
    }

    /**
     * Devolve o objecto área de atividade da competência técnica.
     *
     * @return objecto área de atividade da competência técnica
     */
    public AreaAtividade getAreaAtividade() {
        return areaAtividade;
    }

    /**
     * Devolve a Lista de Graus Proficiencia da competência técnica.
     *
     * @return objecto área de atividade da competência técnica
     */
    public List<GrauProficiencia> getListaGrausProficiencia() {
        return grausProficiencia;
    }

    /**
     * Define a área de atividade da competência técnica.
     *
     * @param areaAtividade nova área de atividade da competência técnica
     */
    public void setAreaAtividade(AreaAtividade areaAtividade) {
        this.areaAtividade = areaAtividade;
    }
    
    // </editor-fold>

    /**
     * Verifica se o id da competência técnica é igual ao id de outra
     * competência técnica, recebido por parêmetro.
     *
     * @param id outro id da competência técnica com o qual se compara o id da
     * competência técnica
     * @return true se o id da competência técnica for igual ao id da
     * competência técnica recebido por parâmetro caso contrário devolve false.
     */
    public boolean hasId(String id) {
        return this.getId().equalsIgnoreCase(id);
    }

    /**
     * Devolve o hashCode do id da competência técnica.
     *
     * @return hashCode do id da competência técnica
     * @see Object#hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     * Compara a competência técnica com o objeto recebido.
     *
     * @param o objeto a comparar com a competência técnica.
     * @return true se o objeto recebido representar um objecto
     * equivalente à competência técnica. Caso contrário, retorna false.
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
        CompetenciaTecnica obj = (CompetenciaTecnica) o;
        return (Objects.equals(getId(), obj.getId()));
    }

    /**
     * Devolve a descrição textual da competência técnica no formato: ID -
     * Descrição Breve - Descrição Detalhada - Área Atividade:
     * areaAtividade.toString().
     *
     * @return caraterísticas da competência técnica
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %s  - Área Atividade: %s", this.getId(), this.getDescBreve(), this.descDetalhada, this.getAreaAtividade().toString());
    }
}
