/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

/**
 * Objecto Caracter de competência técnica
 * @author diana
 */
public class CaraterCT {

    private String idCategoriaTarefa;
    private String codCompetenciaTecnica;
    private int obrigatorio;
    private String grauMinimoProficiencia;

    /**
     * Constrói uma instância de Carácter Competência Técnica recebendo os
     * valores por parâmetro. Só é criada se todos os parâmetros forem válidos.
     *
     * @param idCategoriaTarefa identificação da categoria de tarefa
     * @param codCompetenciaTecnica identificação da competência técnica
     * @param obrigatorio obrigatoriedade da competência técnica
     * @param grauMinimoProficiencia grau minímo de proficiencia associado a
     * cada competência técnica
     */
    public CaraterCT(String idCategoriaTarefa, String codCompetenciaTecnica, int obrigatorio, String grauMinimoProficiencia) {
        if (validateData(codCompetenciaTecnica, obrigatorio, grauMinimoProficiencia)) {
            setIdCategoriaTarefa(idCategoriaTarefa);
            setCodCompetenciaTecnica(codCompetenciaTecnica);
            setObrigatorio(obrigatorio);
            setGrauMinimoProficiencia(grauMinimoProficiencia);
        }
    }
    
    /**
     * Constrói uma instância de Carácter Competência Técnica recebendo os
     * valores por parâmetro. Só é criada se todos os parâmetros forem válidos.
     * 
     * @param codCompetenciaTecnica identificação da competência técnica
     * @param obrigatorio obrigatoriedade da competência técnica
     * @param grauMinimoProficiencia grau minímo de proficiencia associado a
     */
     public CaraterCT(String codCompetenciaTecnica, int obrigatorio, String grauMinimoProficiencia) {
        if (validateData(codCompetenciaTecnica, obrigatorio, grauMinimoProficiencia)) {
            setCodCompetenciaTecnica(codCompetenciaTecnica);
            setObrigatorio(obrigatorio);
            setGrauMinimoProficiencia(grauMinimoProficiencia);
        }
    }

    /**
     * Verifica se cada parâmetro de entrada é nulo ou vazio.
     * 
     * @param idCategoriaTarefa identificação da categoria de tarefa
     * @param codCompetenciaTecnica identificação da competência técnica
     * @param obrigatorio obrigatoriedade da competência técnica
     * @param grauMinimoProficiencia grau minímo de proficiencia associado a
     * @return true se todos os parâmetros forem válidos, caso contrário devolve
     * false com uma mensagem de erro
     */
    private boolean validateData(String codCompetenciaTecnica, int obrigatorio, String grauMinimoProficiencia) {
        if ((grauMinimoProficiencia.isEmpty()) || (codCompetenciaTecnica.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        } else {
            return true;
        }
    }
    
     //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve a identificação da categoria de tarefa.
     * @return identificação da categoria de tarefa
     */
    public String getIdCategoriaTarefa() {
        return idCategoriaTarefa;
    }

    /**
     * Modifica a identificação da categoria de tarefa.
     * @param idCategoriaTarefa nova identificação da categoria de tarefa
     */
    public void setIdCategoriaTarefa(String idCategoriaTarefa) {
        String builtId = "CatTar-"+idCategoriaTarefa;
        this.idCategoriaTarefa = idCategoriaTarefa;
    }

    /**
     * Devolve o código da competência técnica.
     * @return código da competência técnica
     */
    public String getCodCompetenciaTecnica() {
        return codCompetenciaTecnica;
    }

    /**
     * Modifica o código da competência técnica.
     * @param codCompetenciaTecnica novo código da competência técnica
     */
    public void setCodCompetenciaTecnica(String codCompetenciaTecnica) {
        this.codCompetenciaTecnica = codCompetenciaTecnica;
    }

    /**
     * Devolve a Obrigatoriedade da competência técnica.
     * @return Obrigatoriedade da competência técnica
     */
    public int getObrigatorio() {
        return obrigatorio;
    }

    /**
     * Modifica a Obrigatoriedade da competência técnica.
     * @param obrigatorio nova Obrigatoriedade da competência técnica
     */
    public void setObrigatorio(int obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    /**
     * Devolve o grau mínimo de proficiência.
     * @return grau mínimo de proficiência
     */
    public String getGrauMinimoProficiencia() {
        return grauMinimoProficiencia;
    }

    /**
     * Modifica o grau mínimo de proficiência.
     * @param grauMinimoProficiencia novo grau mínimo de proficiência 
     */
    public void setGrauMinimoProficiencia(String grauMinimoProficiencia) {
        this.grauMinimoProficiencia = grauMinimoProficiencia;
    }
    
    // </editor-fold>

    /**
     * Compara o CaracterCT com o objeto recebido.
     *
     * @param o objeto a comparar com o CaracterCT.
     * @return true se o objeto recebido representar um carácter de competência técnica
     * equivalente ao carácter de competência técnica. Caso contrário, retorna false.
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
        CaraterCT obj = (CaraterCT) o;
        //return (Objects.equals(id, obj.id));
        return this.getIdCategoriaTarefa() == obj.getIdCategoriaTarefa() 
                && this.getCodCompetenciaTecnica().equalsIgnoreCase(obj.getCodCompetenciaTecnica()) 
                && this.getObrigatorio() == obj.getObrigatorio() &&
                this.getGrauMinimoProficiencia().equalsIgnoreCase(obj.getGrauMinimoProficiencia());
    }

    /**
     * Devolve a descrição textual do caracter da competência técnica no formato: 
     * ID da categoria de tarefa -- Código da competência técnica -- Obrigatoriedade -- grau minímo de proficiencia.
     *
     * @return caraterísticas do caracter da competência técnica 
     */
    @Override
    public String toString() {
        return String.format("%s -- %s -- %s -- %s", this.getIdCategoriaTarefa(), this.getCodCompetenciaTecnica(), this.getObrigatorio(), this.getGrauMinimoProficiencia());
    }

   
}
