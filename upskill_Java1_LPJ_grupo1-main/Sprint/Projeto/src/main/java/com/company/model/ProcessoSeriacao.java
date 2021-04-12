/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
import java.sql.Date; 
import java.util.List;

/**
 * Objecto Processo de Seriação
 * @author joaor
 */
public class ProcessoSeriacao {
    
    private Date dataRealizacao;
    private Colaborador colab;
    private Anuncio anuncio;
    private List<Colaborador> participantes;
    private ArrayList<Classificacao> classificacoes;
    
    /**
     * Constrói uma instância de Processo de Seriação recebendo os atributos por parâmetro.
     * Só é criada se todos os parâmetros forem válidos.
     * 
     * @param dataRealizacao data de realização do processo de seriação
     * @param colab colaborador referente ao processo de seriação
     * @param anuncio anúncio referente ao processo de seriação
     * @param participantes participantes no processo de seriação
     * @param classificacoes classificações do processo de seriação
     */
    public ProcessoSeriacao(Date dataRealizacao,Colaborador colab, Anuncio anuncio, List<Colaborador> participantes,ArrayList<Classificacao> classificacoes){
        if(validaData(dataRealizacao,colab,anuncio,participantes,classificacoes)){
            setDataRealizacao(dataRealizacao);
            setColab(colab);
            setAnuncio(anuncio);
            this.classificacoes = new ArrayList<>();
            this.participantes = new ArrayList<>();
            setParticipantes(participantes);
            setClassificacoes(classificacoes);
        }
    }

    private boolean validaData(Date dataReconhecimento, Colaborador colab, Anuncio anuncio, List<Colaborador> participantes, ArrayList<Classificacao> classificacoes) {
        if(dataReconhecimento == null || colab == null || anuncio==null || participantes==null || classificacoes==null){
            throw new IllegalArgumentException("Nenhum dos Argumentos pode estar nulo ou vazio.");
        }
        return true;
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
     /**
     * Devolve a data de realização do processo de seriação.
     * @return data de realização do processo de seriação
     */
    public Date getDataRealizacao() {
        return dataRealizacao;
    }
    
    /**
     * Modifica a data de realização do processo de seriação.
     * @param dataRealizacao nova data de realização do processo de seriação
     */
    private void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

     /**
     * Devolve o colaborador referente ao processo de seriação.
     * @return colaborador referente ao processo de seriação
     */
    public Colaborador getColab() {
        return colab;
    }
    
    /**
     * Modifica o colaborador referente ao processo de seriação.
     * @param colab novo colaborador referente ao processo de seriação
     */
    private void setColab(Colaborador colab) {
        this.colab = colab;
    }

    /**
     * Modifica o anúncio referente ao processo de seriação.
     * @return anúncio referente ao processo de seriação
     */
    public Anuncio getAnuncio() {
        return anuncio;
    }
    
    /**
     * Modifica o anúncio referente ao processo de seriação.
     * @param anuncio novo anúncio referente ao processo de seriação
     */
    private void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

     /**
     * Devolve a lista dos participantes no processo de seriação.
     * @return lista dos participantes no processo de seriação
     */
    public List<Colaborador> getParticipantes() {
        return participantes;
    }
    
    /**
     * Modifica a lista dos participantes no processo de seriação.
     * @param participantes nova lista de participantes no processo de seriação
     */
    private void setParticipantes(List<Colaborador> participantes) {
        this.participantes.addAll(participantes);
    }

     /**
     * Devolve a lista de classificações do processo de seriação
     * @return lista de classificações do processo de seriação
     */
    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }
    
    /**
     * Modifica a lista de classificações do processo de seriação
     * @param classificacoes nova lista classificações do processo de seriação
     */
    private void setClassificacoes(ArrayList<Classificacao> classificacoes) {
        this.classificacoes.addAll(classificacoes);
    }
    
    // </editor-fold>
            
     /**
     * Compara o processo de seriação com o objeto recebido.
     *
     * @param o objeto a comparar com o processo de seriação.
     * @return true se o objeto recebido representar um objecto equivalente ao
     * processo de seriação. Caso contrário, retorna false.
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
        ProcessoSeriacao obj = (ProcessoSeriacao) o;
        //return (Objects.equals(id, obj.id));
        return (this.getAnuncio().getTarefa().getRef().equals(obj.getAnuncio().getTarefa().getRef()) && this.getColab().equals(obj.getColab()));
    }

}
