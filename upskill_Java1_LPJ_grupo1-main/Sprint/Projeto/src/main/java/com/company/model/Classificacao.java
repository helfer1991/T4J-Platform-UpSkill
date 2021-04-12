/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;

/**
 * Objecto Classificação
 * @author Asus
 */
public class Classificacao {

    private int lugarClassificacao;
    private Date dataHora;
    private Candidatura candidatura;

    /**
     * Constrói uma instância de Classificacao recebendo os atributos por parâmetro. Só é criada se todos os parâmetros forem válidos.
     * @param lugarClassificacao lugar na classificação
     * @param dataHora data e hora da classificação
     * @param candidatura candidatura 
     */
    public Classificacao(int lugarClassificacao, Date dataHora, Candidatura candidatura) {
        if (validadeData(lugarClassificacao, dataHora, candidatura)) {
            setLugarClassificacao(lugarClassificacao);
            setDataHora(dataHora);
            setCandidatura(candidatura);
        }
    }

    private boolean validadeData(int lugarClassificacao, Date dataHora, Candidatura candidatura) {
        if (lugarClassificacao == 0 || dataHora == null || candidatura == null) {
            throw new IllegalArgumentException("Nenhum dos Argumentos pode estar nulo ou vazio.");
        }
        return true;
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve o lugar na classificação.
     * @return lugar na classificação
     */
    public int getLugarClassificacao() {
        return lugarClassificacao;
    }

    /**
     * Modifica o lugar na classificação.
     * @param lugarClassificacao novo lugar na classificação
     */
    public void setLugarClassificacao(int lugarClassificacao) {
        this.lugarClassificacao = lugarClassificacao;
    }

    /**
     * Devolver a data e hora da classificação.
     * @return data e hora da classificação 
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * Modifica a data e hora da classificação.
     * @param dataHora nova data e hora da classificação
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Devolve a candidatura.
     * @return nova candidatura
     */
    public Candidatura getCandidatura() {
        return candidatura;
    }

    /**
     * Modifica a candidatura.
     * @param candidatura nova candidatura
     */
    public void setCandidatura(Candidatura candidatura) {
        this.candidatura = candidatura;
    }
    // </editor-fold>

    /**
     * Compara a classificação com o objeto recebido.
     *
     * @param o objeto a comparar com a classificação.
     * @return true se o objeto recebido representar um objecto
     * equivalente à classificação. Caso contrário, retorna false.
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
        Classificacao obj = (Classificacao) o;
        //return (Objects.equals(id, obj.id));
        return (this.getLugarClassificacao() == obj.getLugarClassificacao() 
                && this.getCandidatura().getAnuncio().getTarefa().getRef().equalsIgnoreCase(obj.getCandidatura().getAnuncio().getTarefa().getRef()));
    }

    /**
     * Devolve a descrição textual da classificação.
     * @return caraterísticas da classificação
     */
    @Override
    public String toString() {
        return String.format("Classificação: %d"
                + "\nValor: %.1f"
                + "\nFreelancer NIF: %s", this.lugarClassificacao, this.candidatura.getValorPretendido(), this.candidatura.getFree().getNif());
    }
}
