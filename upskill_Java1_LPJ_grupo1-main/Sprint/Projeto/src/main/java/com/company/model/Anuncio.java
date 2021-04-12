/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;

/**
 * Objecto Anúncio
 * @author diana
 */
public class Anuncio {
    
    private Date dInicioPublicitacao;
    private Date dFimPublicitacao;
    private Date dInicioCandidatura;
    private Date dFimCandidatura;
    private Date dInicioSeriacao;
    private Date dFimSeriacao;
    private TipoRegimento regimento;
    private Tarefa tar;
    private Colaborador colab;

    /**
     * Constrói uma instância de Anúncio recebendo os atributos por parâmetro. Só é criada se todos os parâmetros forem válidos.
     * 
     * @param dtInicioPublicitacao data de início da publicação do anúncio da tarefa
     * @param dtFimPublicitacao data de fim da publicação do anúncio da tarefa
     * @param dtInicioCandidatura data de início da candidatura ao anúncio da tarefa
     * @param dtFimCandidatura data de fim da candidatura ao anúncio da tarefa
     * @param dtInicioSeriacao data de início da ordenação dos candidatos ao anúncio da tarefa
     * @param dtFimSeriacao data de fim da ordenação dos candidatos ao anúncio da tarefa
     * @param tarefa tarefa a ser publicada
     * @param colaborador colaborador que publica a tarefa
     * @param regimento tipo de regimento aplicado à ordenação dos candidatos
     */
    public Anuncio( Date dtInicioPublicitacao, Date dtFimPublicitacao, Date dtInicioCandidatura, Date dtFimCandidatura,
            Date dtInicioSeriacao, Date dtFimSeriacao, Tarefa tarefa, Colaborador colaborador, TipoRegimento regimento) {
        if ((dtInicioPublicitacao == null) 
                || (dtFimPublicitacao == null) 
                || (dtInicioCandidatura == null) 
                || (dtFimCandidatura == null) 
                || (dtInicioSeriacao == null) 
                || (dtFimSeriacao == null) 
                || (tarefa == null)
                || (colaborador == null)
                || (regimento == null)) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou ter ordens inválidas.");
        }
        
        setdInicioPublicitacao(dtInicioPublicitacao);
        setdFimPublicitacao(dtFimPublicitacao);
        setdInicioCandidatura(dtInicioCandidatura);
        setdFimCandidatura(dtFimCandidatura);
        setdInicioSeriacao(dtInicioSeriacao);
        setdFimSeriacao(dtFimSeriacao);
        setRegimento(regimento);
        this.tar = tarefa;
        this.colab = colaborador;

    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    /**
     * Devolve a data de início da publicação do anúncio da tarefa.
     * 
     * @return data de início da publicação
     */
    public Date getdInicioPublicitacao() {
        return dInicioPublicitacao;
    }

    /**
     * Modifica a data de início da publicação do anúncio da tarefa.
     * 
     * @param dInicioPublicitacao nova data de início da publicação
     */
    public void setdInicioPublicitacao(Date dInicioPublicitacao) {
        this.dInicioPublicitacao = dInicioPublicitacao;
    }

    /**
     * Devolve a data de fim da publicação do anúncio da tarefa.
     * 
     * @return data de fim da publicação
     */
    public Date getdFimPublicitacao() {
        return dFimPublicitacao;
    }

    /**
     * Modifica a data de fim da publicação do anúncio da tarefa.
     * 
     * @param dFimPublicitacao nova data de fim da publicação
     */
    public void setdFimPublicitacao(Date dFimPublicitacao) {
        this.dFimPublicitacao = dFimPublicitacao;
    }

    /**
     * Devolve a data de início da candidatura ao anúncio da tarefa.
     * 
     * @return data de início da candidatura
     */
    public Date getdInicioCandidatura() {
        return dInicioCandidatura;
    }

    /**
     * Modifica a data de início da candidatura ao anúncio da tarefa.
     * 
     * @param dInicioCandidatura nova data de início da candidatura
     */
    public void setdInicioCandidatura(Date dInicioCandidatura) {
        this.dInicioCandidatura = dInicioCandidatura;
    }

    /**
     * Devolve a data de fim da candidatura ao anúncio da tarefa.
     * 
     * @return data de fim da candidatura
     */
    public Date getdFimCandidatura() {
        return dFimCandidatura;
    }

    /**
     * Modifica a data de fim da candidatura ao anúncio da tarefa.
     * 
     * @param dFimCandidatura nova data de fim da candidatura
     */
    public void setdFimCandidatura(Date dFimCandidatura) {
        this.dFimCandidatura = dFimCandidatura;
    }

    /**
     * Devolve a data de início da ordenação dos candidatos ao anúncio da tarefa.
     * 
     * @return data de início da seriacão
     */
    public Date getdInicioSeriacao() {
        return dInicioSeriacao;
    }

    /**
     * Modifica a data de início da ordenação dos candidatos ao anúncio da tarefa.
     * 
     * @param dInicioSeriacao nova data de início da seriacão
     */
    public void setdInicioSeriacao(Date dInicioSeriacao) {
        this.dInicioSeriacao = dInicioSeriacao;
    }

    /**
     * Devolve a data de fim da ordenação dos candidatos ao anúncio da tarefa.
     * 
     * @return data de fim da seriacão
     */
    public Date getdFimSeriacao() {
        return dFimSeriacao;
    }

    /**
     * Modifica a data de fim da ordenação dos candidatos ao anúncio da tarefa.
     * 
     * @param dFimSeriacao nova data de fim da seriacão
     */
    public void setdFimSeriacao(Date dFimSeriacao) {
        this.dFimSeriacao = dFimSeriacao;
    }

    /**
     * Devolve o tipo de regimento aplicado à ordenação dos candidatos.
     * 
     * @return tipo de regimento aplicado
     */
    public TipoRegimento getRegimento() {
        return regimento;
    }

    /**
     * Modifica o tipo de regimento aplicado à ordenação dos candidatos.
     * 
     * @param regimento novo tipo de regimento aplicado
     */
    public void setRegimento(TipoRegimento regimento) {
        this.regimento = regimento;
    }
    
     /**
     * Devolve a tarefa que dá origem ao anúncio.
     * 
     * @return tarefa que origina um anúncio
     */
    public Tarefa getTarefa() {
        return tar;
    }

    /**
     * Modifica a tarefa que dá origem ao anúncio.
     * 
     * @param tarefa nova tarefa que origina um anúncio
     */
    public void setTarefa(Tarefa tarefa) {
        this.tar = tarefa;
    }

    /**
     * Devolve o colaborador que criou o anúncio.
     * 
     * @return colaborador que criou o anúncio
     */
    public Colaborador getColab() {
        return colab;
    }
        // </editor-fold>
    
    
     /**
     * Compara o Anuncio com o objeto recebido.
     * 
     * @param o objeto a comparar com o Anúncio.
     * @return true se o objeto recebido representar um objecto
     * equivalente ao Anúncio. Caso contrário, retorna false.
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
        Anuncio obj = (Anuncio) o;

        return this.getdInicioPublicitacao().equals(obj.getdInicioPublicitacao())
                && this.getdFimPublicitacao().equals(obj.getdFimPublicitacao())
                && this.getdInicioCandidatura().equals(obj.getdInicioCandidatura())
                && this.getdFimCandidatura().equals(obj.getdFimCandidatura())
                && this.getdInicioSeriacao().equals(obj.getdInicioSeriacao())
                && this.getdFimSeriacao().equals(obj.getdFimSeriacao())
                && this.getRegimento().equals(obj.getRegimento())
                && this.getTarefa().getRef().equalsIgnoreCase(obj.getTarefa().getRef())
                && this.getColab().getEmail().equalsIgnoreCase(obj.getColab().getEmail());
                
    }

    /**
     * Devolve a descrição textual do Anúncio no formato: 
     * Início da publicação do anúncio: \n 
     * Fim da publicação do anúncio: \n 
     * Início da candidatura ao anúncio: \n 
     * Fim da candidatura ao anúncio: \n 
     * Início da seriação dos candidatos: \n 
     * Fim da seriação dos candidatos: \n 
     * Regimento aplicado à seriação: \n
     *
     * @return caraterísticas do Anúncio
     */
    @Override
    public String toString() {
        return String.format("Referencia: %s\nInício da publicação do anúncio: %s\n Fim da publicação do anúncio: %s\n Início da candidatura ao anúncio: %s\n Fim da candidatura ao anúncio: %s\n Início da seriação dos candidatos: %d\n Fim da seriação dos candidatos: %2f\n Regimento aplicado à seriação: %s",
                this.getTarefa().getRef(), this.getdInicioPublicitacao(), this.getdFimPublicitacao(), this.getdInicioCandidatura(), this.getdFimCandidatura(), this.getdInicioSeriacao(), this.getdFimSeriacao(), this.getRegimento().toString());
    }

}