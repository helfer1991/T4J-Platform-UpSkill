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
 * Objecto Organização
 * @author Asus
 */
public class Organizacao {
    private static final String PATTERN_NUM = "[0-9]+";
    private static final String PATTERN_NOME = "[a-zA-Z]+";
    private static final String PATTERN_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String PATTERN_SITE = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
    private String nome;
    private String nif;
    private EnderecoPostal endPostal;
    private String site;
    private String telefone;
    private String email;
    private Colaborador gestor;
    private List<Colaborador> listaColaborador = new ArrayList<>();

    /**
     * Constrói uma instância da classe Organização recebendo o nome, o número de identificação fiscal,
     * o site, o número de telefone, o e-mail, a morada, e o colaborador que faz o registo da organização.
     * Só é criada se todos os parâmetros forem válidos.
     * 
     * @param nome nome da organização
     * @param nif número de identificação fiscal da organização
     * @param site endereço web da organização
     * @param telefone contacto telefónico da organização
     * @param email e-mail da organização
     * @param morada endereço postal da organização
     * @param colab colaborador(por exemplo um gestor) que está a proceder ao registo da organização
     */
    public Organizacao(String nome, String nif, String site, String telefone,String email, EnderecoPostal morada, Colaborador colab) throws SQLException {
        if ((nome == null) || (nif == null) || (telefone == null)
                || (email == null) || (morada == null) || (colab == null)
                || (nome.isEmpty()) || (nif.isEmpty()) || (telefone.isEmpty())
                || (email.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }

        setNome(nome);
        setNif(nif);
        setEndPostal(morada);
        setSite(site);
        setTelefone(telefone);
        setEmail(email);
        setGestor(colab);

    }  
    
    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve o nome da organização.
     * @return nome da organização
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Modifica o nome da Organização.
     * Verifica se apenas contém caracteres alfabéticos.
     * 
     * @param nome nome da Organização no padrão "[a-zA-Z]+"
     */
    private void setNome(String nome) {
        if (nome.matches(PATTERN_NOME)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não deve conter números.");
        }
    }

    /**
     * Devolve o número de identificação fiscal da organização.
     * @return número de identificação fiscal da organização
     */
    public String getNif() {
        return nif;
    }
    
    /**
     * Modifica o número de identificação fiscal da Organização.
     * Verifica se é do tipo numérico.
     * 
     * @param nif número de identificação fiscal da organização no padrão "[0-9]+"
     */
    private void setNif(String nif) {
        if (nif.matches(PATTERN_NUM)) {
            this.nif = nif;
        } else {
            throw new IllegalArgumentException("Nif deve ser do tipo numérico.");
        }
    }
    
     /**
     * Devolve o endereço web da organização.
     * @return endereço web da organização
     */
    public String getSite() {
        return site;
    }
    
    /**
     * Modifica o endereço web da Organização
     * Valida se os caracteres estão dentro do padrão definido.
     * 
     * @param site endereço web da Organização no padrão 
     * "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$"
     */
    private void setSite(String site) {
        if (site.matches(PATTERN_SITE)) {
            this.site = site;
        } else {
            throw new IllegalArgumentException("Site Inválido");
        }
    }

    /**
     * Devolve o contacto telefónico da organização.
     * @return contacto telefónico da organização
     */
    public String getTelefone() {
        return telefone;
    }
    
    /**
     * Define o contacto telefónico da Organização.
     * O contacto telefónico apenas assume o novo formato se for numérico,
     * se não o for apresenta uma mensagem de erro.
     * 
     * @param telefone contacto telefónico da organização no padrão "[0-9]+"
     */
    private void setTelefone(String telefone) {
        if (telefone.matches(PATTERN_NUM)) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone deve ser do tipo numérico.");
        }
    }

    /**
     * Devolve o e-mail da organização.
     * @return e-mail da organização
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Define o e-mail da Organização.
     * Valida se os caracteres estão dentro do padrão definido.
     * 
     * @param email e-mail da organização no padrão "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"
     */
    private void setEmail(String email) {
        if (email.matches(PATTERN_EMAIL)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email Invalido");
        }
    }
    
     /**
     * Devolve o endereço postal da organização.
     * @return endereço postal da organização
     */
    public EnderecoPostal getEndPostal() {
        return endPostal;
    }
    
    /**
     * Define o endereço postal da Organização.
     * 
     * @param endPostal endereço postal da Organização
     */
    private void setEndPostal(EnderecoPostal endPostal) {
        this.endPostal = endPostal;
    }

    /**
     * Devolve o objecto Gestor.
     * @return objecto Gestor do tipo Colaborador
     */
    public Colaborador getGestor() {
        return this.gestor;
    }
    
    /**
     * Define o novo gestor da Organização e adiciona-o ao repositório de colaboradores.
     * 
     * @param gestor objecto Gestor do tipo Colaborador
     */
    private void setGestor(Colaborador gestor) throws SQLException {
        this.gestor = gestor;
        //this.getRepositorioColaborador().registaColaborador(Constantes.ROLE_GESTOR_ORGANIZACAO,gestor);
    }
    
    // </editor-fold>
    
    /**
     * Devolve uma lista de colaboradores da organização.
     * @return lista de colaboradores da organização
     */
    public List<Colaborador> getListaColaborador() {
        return listaColaborador;
    }

    /**
     * Modifica a lista de colaboradores da organização.
     * @param listaColaborador nova lista de colaboradores da organização
     */
    public void setListaColaborador(List<Colaborador> listaColaborador) {
        this.listaColaborador = listaColaborador;
    }
    
    /**
     * Devolve um novo objecto do tipo Colaborador construído com os parâmetros de entrada.
     * 
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     * @return novo objecto do tipo Colaborador
     */
    public static Colaborador novoColaborador(String nome, String telefone, String email) {
        return new Colaborador(nome, telefone, email);
    }

    /**
     * Devolve um novo objecto do tipo Endereço Postal construído com os parâmetros de entrada.
     * 
     * @param rua rua do endereço postal
     * @param codPostal código postal do endereço postal
     * @param localidade localidade do endereço postal
     * @return novo objecto do tipo Endereço Postal
     */
    public static EnderecoPostal novoEnderecoPostal(String rua, String codPostal, String localidade) {
        return new EnderecoPostal(rua, codPostal, localidade);
    }

    

    /**
     * /**
     * Devolve o hashCode do número de identificação fiscal da Organização.
     *
     * @return hashCode do número de identificação fiscal da Organização
     * @see Object#hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.getNif());
        return hash;
    }

    /**
     * Compara a Organização com o objeto recebido.
     *
     * @param o objeto a comparar com a Organização
     * @return true se o objeto recebido representar um objecto
     * equivalente à organização. Caso contrário, retorna false.
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
        Organizacao obj = (Organizacao) o;
        return (Objects.equals(getNif(), obj.getNif()));
    }

    /**
     * Devolve a descrição textual da Organização no formato: Nome -
     * NIF - Site - Telefone - Email - Endereço postal - Gestor.
     *
     * @return caraterísticas da Organização
     */
    @Override
    public String toString() {
        String str = String.format("%s - %s - %s - %s - %s - %s - %s", this.getNome(), this.getNif(), this.getSite(), this.getTelefone(), this.getEmail(), this.getEndPostal().toString(), this.gestor.toString());
        return str;
    }

}
