/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 * Objecto Colaborador
 * @author Asus
 */
public class Colaborador {

    private static final String PATTERN_TELE = "[0-9]+";
    private static final String PATTERN_NOME = "[a-zA-Z ]+";
    private static final String PATTERN_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private String nome;
    private String telefone;
    private String email;

    /**
     * Constrói uma instância de Colaborador recebendo o nome, o contacto
     * telefónico e o e-mail.
     *
     * Só é criada se todos os parâmetros forem válidos.
     *
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     */
    public Colaborador(String nome, String telefone, String email) {
        if (validateData(nome, telefone, email)) {
            setNome(nome);
            setEmail(email);
            setTelefone(telefone);
        }
    }

    /**
     * Verifica se cada parâmetro de entrada é nulo ou vazio.
     *
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     * @return true se todos os parâmetros forem válidos, caso contrário devolve
     * false com uma mensagem de erro
     */
    private boolean validateData(String nome, String telefone, String email) {
        if ((nome == null) || (telefone == null) || (email == null)
                || (nome.isEmpty()) || (telefone.isEmpty()) || (email.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        } else {
            return true;
        }
    }

    /**
     * Verifica se o e-mail do Colaborador é igual ao e-mail de outro
     * Colaborador, recebido por parâmetro.
     *
     * @param email outro e-mail de Colaborador com o qual se compara o e-mail
     * do Colaborador
     * @return true se o e-mail do Colaborador for igual ao e-mail do
     * Colaborador recebido por parâmetro caso contrário devolve false.
     */
    public boolean hasEmail(String email) {
        return this.email.equalsIgnoreCase(email);
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    /**
     * Devolve o nome do Colaborador.
     *
     * @return nome do Colaborador
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o nome do Colaborador. O nome apenas é válido se não tiver
     * caracteres numéricos, caso contrário apresenta uma mensagem de erro.
     *
     * @param nome nome do Colaborador
     */
    private void setNome(String nome) {
        if (nome.matches(PATTERN_NOME)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não deve conter números.");
        }
    }

    /**
     * Devolve o telefone do Colaborador.
     *
     * @return telefone do Colaborador
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do Colaborador. O telefone apenas é válido se só tiver
     * caracteres numéricos, caso contrário apresenta uma mensagem de erro.
     *
     * @param telefone telefone do Colaborador
     */
    private void setTelefone(String telefone) {
        if (telefone.matches(PATTERN_TELE)) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone deve ser do tipo numérico.");
        }
    }

    /**
     * Devolve o e-mail do Colaborador.
     *
     * @return e-mail do Colaborador
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Define o e-mail do Colaborador. O e-mail apenas é válido se tiver o
     * formato "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"
     * caso contrário apresenta uma mensagem de erro.
     *
     * @param email e-mail do Colaborador
     */
    private void setEmail(String email) {
        if (email.matches(PATTERN_EMAIL)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email Invalido");
        }
    }
    // </editor-fold>

    /**
     * Devolve o hashCode do e-mail do Colaborador.
     *
     * @return hashCode do e-mail do Colaborador
     * @see Object#hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     * Compara o Colaborador com o objeto recebido.
     *
     * @param o objeto a comparar com o Colaborador.
     * @return true se o objeto recebido representar um objecto equivalente
     * ao Colaborador. Caso contrário, retorna false.
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
        Colaborador obj = (Colaborador) o;
        return (Objects.equals(email, obj.email) && Objects.equals(nome, obj.nome));
    }

    /**
     * Devolve a descrição textual do Colaborador no formato: Nome - Telefone - e-mail.
     *
     * @return caraterísticas do Colaborador
     */
    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", this.nome, this.getTelefone(), this.email);
    }

}
