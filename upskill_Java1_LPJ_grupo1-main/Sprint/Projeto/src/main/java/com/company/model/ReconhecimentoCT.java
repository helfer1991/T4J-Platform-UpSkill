/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

/**
 * Objecto Reconhecimento de competência técnica
 * @author Asus
 */
public class ReconhecimentoCT {

    private Date dataReconhecimento;
    private GrauProficiencia grauReconhecido;

    /**
     * Constrói uma instância de reconhecimento da competência técnica recebendo os atributos por parâmetro.
     * Só é criada se todos os parâmetros forem válidos.
     * 
     * @param dataReconhecimento data de reconhecimento da competência técnica
     * @param grauReconhecido grau de reconhecimento da competência técnica
     */
    public ReconhecimentoCT(Date dataReconhecimento, GrauProficiencia grauReconhecido) {
        if (validaData(dataReconhecimento, grauReconhecido)) {
            setDataReconhecimento(dataReconhecimento);
            setGrauReconhecido(grauReconhecido);
        }
    }

    private boolean validaData(Date dataReconhecimento, GrauProficiencia grauReconhecido) {
        if (dataReconhecimento == null  || grauReconhecido == null) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        return true;
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">

    /**
     * Devolve a data de reconhecimento da competência técnica.
     * @return data de reconhecimento da competência técnica
     */
    public Date getDataReconhecimento() {
        return dataReconhecimento;
    }

    /**
     * Modifica a data de reconhecimento da competência técnica.
     * @param dataReconhecimento nova data de reconhecimento da competência técnica
     */
    private void setDataReconhecimento(Date dataReconhecimento) {
        Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if (dataReconhecimento.after(now)) {
            throw new IllegalArgumentException("Data de Reconhecimento não pode ser superior a data de hoje.");
        } else {
            this.dataReconhecimento = dataReconhecimento;
        }
    }

    /**
     * Devolve o grau de reconhecimento da competência técnica.
     * @return grau de reconhecimento da competência técnica
     */
    public GrauProficiencia getGrauReconhecido() {
        return grauReconhecido;
    }

    /**
     * Modifica o grau de reconhecimento da competência técnica.
     * @param grauReconhecido novo grau de reconhecimento da competência técnica
     */
    private void setGrauReconhecido(GrauProficiencia grauReconhecido) {
        this.grauReconhecido = grauReconhecido;
    }
    // </editor-fold>
    
    /**
     * Compara o reconhecimento da competência técnica com o objeto recebido.
     *
     * @param o objeto a comparar com o reconhecimento da competência técnica.
     * @return true se o objeto recebido representar um objecto
     * equivalente ao reconhecimento da competência técnica. Caso contrário, retorna false.
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
        ReconhecimentoCT obj = (ReconhecimentoCT) o;
        return (Objects.equals(getGrauReconhecido(), obj.getGrauReconhecido()));
    }

}
