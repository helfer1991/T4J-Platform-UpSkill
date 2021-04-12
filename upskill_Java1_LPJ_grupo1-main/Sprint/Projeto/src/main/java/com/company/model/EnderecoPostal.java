/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 * Objecto Endereço Postal
 * @author Asus
 */
public class EnderecoPostal {

    private static final String PATTERN_ZIP = "^(\\d{4}(?:\\-\\d{3})?)$";
    private static final String PATTERN_LOCALIDADE = /*"[a-zA-Z]+";*/ "^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$";

    private String rua;
    private String codPostal;
    private String localidade;

    /**
     * Constrói uma instância de Endereço Postal recebendo a rua, o código postal e a localidade.
     * Só é criada se todos os parâmetros forem válidos.
     * 
     * @param rua rua do endereço postal
     * @param codPostal código postal do endereço postal
     * @param localidade localidade do endereço postal
     */
    public EnderecoPostal(String rua, String codPostal, String localidade) {
        if ((rua == null) || (codPostal == null) || (localidade == null)
                || (rua.isEmpty()) || (codPostal.isEmpty()) || (localidade.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        setRua(rua);
        setCodPostal(codPostal);
        setLocalidade(localidade);

    }

    /**
     * Devolve o hashCode do objecto EnderecoPostal.
     *
     * @return hashCode do objecto EnderecoPostal
     * @see Object#hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hash(this.getRua(), this.getCodPostal(), this.getLocalidade());
        return hash;
    }
    
     //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
     /**
     * Devolve a rua do endereço postal.
     * @return rua do endereço postal
     */
    public String getRua() {
        return rua;
    }
    
    /**
     * Modifica a rua do endereço postal.
     * Verifica se o tamanho do novo endereço postal tem menos de 50 caracteres, 
     * caso contrário apresenta mensagem de erro.
     * 
     * @param rua nova rua do endereço postal
     */
    public void setRua(String rua) {
        if (rua.length() < 50) {
            this.rua = rua;
        } else {
            throw new IllegalArgumentException("Rua não deve ter mais do que 50 caracteres.");
        }
    }
    
    /**
     * Devolve o código postal do endereço postal.
     * @return código postal do endereço postal
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Modifica o código postal do endereço postal.
     *
     * @param codPostal código postal
     */
     public void setCodPostal(String codPostal) {
        
            this.codPostal = codPostal;
        
    }
     
     /**
     * Devolve a localidade do endereço postal.
     * @return localidade do endereço postal
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * Modifica a localidade do endereço postal.
     * Verifica se a nova localidade não tem números e se tem menos de 50 caracteres, 
     * caso contrário apresenta mensagem com um dos erros.
     * 
     * @param localidade localidade no padrão "^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$"
     */
    public void setLocalidade(String localidade) {
        if (localidade.matches(PATTERN_LOCALIDADE)) {
            if (localidade.length() < 50) {
                this.localidade = localidade;
            } else {
                throw new IllegalArgumentException("A Localidade não deve ter mais que 50 caracteres.");
            }
        } else {
            throw new IllegalArgumentException("A Localidade não pode conter carateres numéricos");
        }
    }
    // </editor-fold>

    /**
     * Compara o endereço postal com o objeto recebido.
     *
     * @param o objeto a comparar com o endereço postal
     * @return true se o objeto recebido representar um endereço postal
     * equivalente ao endereço postal. Caso contrário, retorna false.
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
        EnderecoPostal obj = (EnderecoPostal) o;
        return (Objects.equals(getRua(), obj.getRua())
                && Objects.equals(getCodPostal(), obj.getCodPostal())
                && Objects.equals(getLocalidade(), obj.getLocalidade()));
    }

    /**
     * Devolve a descrição textual do endereço postal no formato: rua \n código postal - localidade
     *
     * @return caraterísticas do endereço postal
     */
    @Override
    public String toString() {
        return String.format("%s \n %s - %s", this.getRua(), this.getCodPostal(), this.getLocalidade());
    }
     
}
