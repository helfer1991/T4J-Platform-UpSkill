/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;

/**
 * Objecto Candidatura
 * @author Asus
 */
public class Candidatura implements Comparable<Candidatura>{
    private int id;
    private Anuncio anun;
    private Date dtCandidatura;
    private double valorPretendido;
    private int nrDias;
    private String txtApr;
    private String txtMotiv;
    private Freelancer free;
    private String isActive;
    
    /**
     * Constrói uma instância de Candidatura recebendo os atributos por parâmetro.
     * Só é criada se todos os parâmetros forem válidos.
     * 
     * @param anun anúncio alvo da candidatura
     * @param dtCandidatura data da candidatura
     * @param valorPretendido valor pretendido 
     * @param nrDias número de dias
     * @param txtApr texto de apresentação
     * @param txtMotiv texto de motivação
     * @param free freelancer
     */
    public Candidatura(Anuncio anun, Date dtCandidatura, double valorPretendido, int nrDias, String txtApr, String txtMotiv, Freelancer free) {
        if(validateData(anun, dtCandidatura, valorPretendido, nrDias, free)) {
            this.anun = anun;
            this.dtCandidatura = dtCandidatura;
            setValorPretendido(valorPretendido);
            setNrDias(nrDias);
            this.txtApr = txtApr;
            this.txtMotiv = txtMotiv;
            this.free = free;
            this.isActive = "true";
        }
    }
    
    private boolean validateData(Anuncio anun, Date dtCandidatura, double valorPretendido, int nrDias, Freelancer free) {
        if ((anun == null) || (dtCandidatura == null) || (valorPretendido == 0) || (nrDias == 0) || (free == null)) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio. Valores numericos devem ser superiores a 0");
        } else {
            return true;
        }
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    /**
     * Devolve o anúncio alvo da Candidatura.
     * @return anúncio alvo da Candidatura
     */
    public Anuncio getAnuncio() {
        return anun;
    }

    /**
     * Modifica o anúncio alvo da Candidatura.
     * @param anuncio novo anúncio alvo da Candidatura
     */
    public void setAnuncio(Anuncio anuncio) {
        this.anun = anuncio;
    }
    
    /**
     * Devolve a identificação da Candidatura.
     * @return identificação da Candidatura.
     */
    public int getId(){
        return id;
    }
    
    /**
     * Modifica a identificação da Candidatura.
     * @param Id nova identificação da Candidatura.
     */
    public void setId(int Id){
        this.id = Id;
    }

    /**
     * Devolve a data da candidatura.
     * @return data da candidatura
     */
    public Date getDtCandidatura() {
        return dtCandidatura;
    }

    /**
     * Modifica a data da candidatura.
     * @param dtCandidatura nova a data da candidatura
     */
    public void setDtCandidatura(Date dtCandidatura) {
        this.dtCandidatura = dtCandidatura;
    }

    /**
     * Devolve o valor pretendido.
     * @return valor pretendido
     */
    public double getValorPretendido() {
        return valorPretendido;
    }

    /**
     * Modifica o valor pretendido.
     * @param valorPretendido novo valor pretendido
     */
    public void setValorPretendido(double valorPretendido) { // PASSAR PARA A DB
        if(valorPretendido < Double.MAX_VALUE) {
            if(valorPretendido > 0) {
                this.valorPretendido = valorPretendido;
            } else {
                throw new IllegalArgumentException("O Valor Pretendido tem de ser positivo"); 
            }
        } else {
            throw new IllegalArgumentException("O Valor Pretendido é demasiado");
        }
    }

    /**
     * Devolve o número de dias
     * @return número de dias
     */
    public int getNrDias() {
        return nrDias;
    }

    /**
     * Modifica o número de dias.
     * @param nrDias novo número de dias
     */
    public void setNrDias(int nrDias) { // PASSAR PARA A DB
        if(nrDias < Integer.MAX_VALUE) {
            if(nrDias > 0) {
                this.nrDias = nrDias;
            } else {
                throw new IllegalArgumentException("Valor do Número de Dias tem de ser positivo"); 
            }
        } else {
            throw new IllegalArgumentException("Valor do Número de Dias grande demais");
        }
    }

    /**
     * Devolve o texto de apresentação.
     * @return texto de apresentação
     */
    public String getTxtApr() {
        return txtApr;
    }

    /**
     * Modifica o texto de apresentação.
     * @param txtApr novo texto de apresentação
     */
    public void setTxtApr(String txtApr) {
        this.txtApr = txtApr;
    }

    /**
     * Devolve o texto de motivação.
     * @return texto de motivação
     */
    public String getTxtMotiv() {
        return txtMotiv;
    }

    /**
     * Modifica o texto de motivação.
     * @param txtMotiv novo texto de motivação
     */
    public void setTxtMotiv(String txtMotiv) {
        this.txtMotiv = txtMotiv;
    }

    /**
     * Devolve o freelancer.
     * @return freelancer
     */
    public Freelancer getFree() {
        return free;
    }

    /**
     * Devolve o estado da Candidatura, se está ativa/inativa.
     * @return estado Candidatura
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Modifica o estado da Candidatura.
     * @param isActive novo estado da Candidatura
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
      // </editor-fold>
    
    
     /**
     * Compara a Candidatura com o objeto recebido.
     * 
     * @param o objeto a comparar com a Candidatura.
     * @return true se o objeto recebido representar um objecto
     * equivalente à Candidatura. Caso contrário, retorna false.
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
        Candidatura obj = (Candidatura) o;
        //return (Objects.equals(id, obj.id));
        return this.anun.equals(obj.getAnuncio()) && this.free.equals(obj.getFree()) && this.nrDias == obj.nrDias && this.valorPretendido == obj.valorPretendido;
    }
    
    /**
     * Compara o valor pretendido de um objecto Candidatura, com o valor pretendido do objecto recebido.
     * @param o objeto a comparar com a Candidatura.
     * @return devolve 1 se o valor pretendido do primeiro for maior que o segundo, caso contrário devolve -1
     */
    @Override
    public int compareTo(Candidatura o) {
        Double a = getValorPretendido();
        Double b = o.getValorPretendido();
        int c = a.compareTo(b);
        return c;
    }
    
    /**
     * Devolve a descrição textual da Candidatura.
     *
     * @return caraterísticas da Candidatura
     */
    @Override
    public String toString() {
        return String.format("Valor Pretendido: %.1f POTs"
                + "\nTempo de execucao: %d Dias"
                + "\nFreelancer"
                + "\nNome: %s"
                + "\nNIF: %s"
                + "\nEmail: %s"
                + "\n", valorPretendido, nrDias, free.getNome(),free.getNif(),free.getEmail());
    }

}