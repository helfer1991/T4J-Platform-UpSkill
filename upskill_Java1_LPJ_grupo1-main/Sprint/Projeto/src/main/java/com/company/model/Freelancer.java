/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Objecto Freelancer
 * @author Asus
 */
public class Freelancer {

    private static final String PATTERN_NUM = "[0-9]+";
    private static final String PATTERN_NOME = "[a-zA-Z ]+";
    private static final String PATTERN_EMAIL = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private String nome;
    private String nif;
    private String email;
    private String telefone;
    private EnderecoPostal endPostal;
    private ArrayList<HabilitacaoAcademica> lHabAc;
    private ArrayList<ReconhecimentoCT> lRecFree;

    /**
     * Constrói uma instância de Freelancer recebendo os atributos por parâmetro. Só é criada se todos os
     * parâmetros forem válidos.
     * 
     * @param nome nome do Freelancer
     * @param nif número de identificação fiscal do Freelancer
     * @param email email do Freelancer
     * @param telefone contacto telefónico do Freelancer
     * @param codPostal código postal do Freelancer
     * @param lHabAc lista de habilitações académicas do Freelancer
     * @param lRecFree lista de reconhecimento de competências técnicas do Freelancer
     */
    public Freelancer(String nome, String nif, String email, String telefone, EnderecoPostal codPostal, ArrayList<HabilitacaoAcademica> lHabAc, ArrayList<ReconhecimentoCT> lRecFree) {
        if (validaData(nome, nif, email, telefone, codPostal, lHabAc, lRecFree)) {
            this.lHabAc = new ArrayList<>();
            this.lRecFree = new ArrayList<>();
            setNome(nome);
            setNif(nif);
            setEmail(email);
            setTelefone(telefone);
            setEndPostal(codPostal);
            setlHabAc(lHabAc);
            setlRecFree(lRecFree);
        }
    }

    private boolean validaData(String nome, String nif, String email, String telefone, EnderecoPostal endPostal, ArrayList<HabilitacaoAcademica> lHabAc, ArrayList<ReconhecimentoCT> lRecFree) {
        if (nome.isEmpty() || nif.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || endPostal == null || lHabAc == null || lRecFree == null) {
            throw new IllegalArgumentException("Nenhum dos dados podem ser vazios ou null");
        }
        return true;
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve o nome do Freelancer.
     * @return nome do Freelancer
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica o nome do Freelancer.
     * @param nome novo nome do Freelancer
     */
    private void setNome(String nome) {
        if (nome.matches(PATTERN_NOME)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não deve conter números.");
        }
    }

    /**
     * Devolve o número de identificação fiscal do Freelancer.
     * @return número de identificação fiscal do Freelancer
     */
    public String getNif() {
        return nif;
    }

    /**
     * Modifica o número de identificação fiscal do Freelancer.
     * @param nif novo número de identificação fiscal do Freelancer
     */
    private void setNif(String nif) {
        if (nif.matches(PATTERN_NUM)) {
            this.nif = nif;
        } else {
            throw new IllegalArgumentException("NIF deve ser do tipo numérico.");
        }
    }

    /**
     * Devolve o email do Freelancer.
     * @return email do Freelancer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica o email do Freelancer.
     * @param email novo email do Freelancer.
     */
    private void setEmail(String email) {
        if (email.matches(PATTERN_EMAIL)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email Invalido");
        }
    }

    /**
     * Devolve o contacto telefónico do Freelancer.
     * @return contacto telefónico do Freelancer
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Modifica o contacto telefónico do Freelancer.
     * @param telefone novo contacto telefónico do Freelancer
     */
    private void setTelefone(String telefone) {
        if (telefone.matches(PATTERN_NUM)) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone deve ser do tipo numérico.");
        }
    }

    /**
     * Devolve o código postal do Freelancer.
     * @return código postal do Freelancer
     */
    public EnderecoPostal getEndPostal() {
        return endPostal;
    }

    /**
     * Modifica o código postal do Freelancer.
     * @param endPostal novo código postal do Freelancer
     */
    private void setEndPostal(EnderecoPostal endPostal) {
        this.endPostal = endPostal;
    }

    /**
     * Devolve a lista de habilitações académicas do Freelancer.
     * @return lista de habilitações académicas do Freelancer
     */
    public ArrayList<HabilitacaoAcademica> getlHabAc() {
        return lHabAc;
    }

    /**
     * Modifica a lista de habilitações académicas do Freelancer.
     * @param lHabAc nova lista de habilitações académicas do Freelancer
     */
    public void setlHabAc(ArrayList<HabilitacaoAcademica> lHabAc) {
        this.lHabAc = lHabAc;
    }

    /**
     * Devolve a lista de reconhecimento de competências técnicas do Freelancer.
     * @return lista de reconhecimento de competências técnicas do Freelancer
     */
    public ArrayList<ReconhecimentoCT> getlRecFree() {
        ArrayList<ReconhecimentoCT> listaCopy = new ArrayList(lRecFree);
        return listaCopy;
    }
    
    /**
     * Modifica a lista de reconhecimento de competências técnicas do Freelancer.
     * @param lRecFree nova lista de reconhecimento de competências técnicas do Freelancer
     */
    public void setlRecFree(ArrayList<ReconhecimentoCT> lRecFree) {
        this.lRecFree.addAll(lRecFree);
    }
    // </editor-fold>
    
    /**
     * Verifica se o email do Freelancer é igual ao email recebido por parâmetro.
     *
     * @param email email com o qual se compara o email do Freelancer
     * @return true se o email for igual ao email do Freelancer recebido por parâmetro caso contrário devolve false.
     */
    public boolean hasEmail(String email) {
        return this.email.equalsIgnoreCase(email);
    }

    /**
     * Compara o Freelancer com o objeto recebido.
     *
     * @param o objeto a comparar com o Freelancer.
     * @return true se o objeto recebido representar um objecto
     * equivalente ao Freelancer. Caso contrário, retorna false.
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
        Freelancer obj = (Freelancer) o; // em baixo deve bastar o email e o nif, verificar depois
        return (Objects.equals(email, obj.email) && Objects.equals(nome, obj.nome) && Objects.equals(nif, obj.nif));
    }

    /**
     * Devolve a descrição textual do Freelancer.
     *
     * @return caraterísticas do Freelancer
     */
    @Override
    public String toString() {
        return String.format("Nome: %s\nNIF: %s\nEmail: %s\nTelefone: %s\n", this.nome, this.nif, this.email, this.telefone);
    }

}
