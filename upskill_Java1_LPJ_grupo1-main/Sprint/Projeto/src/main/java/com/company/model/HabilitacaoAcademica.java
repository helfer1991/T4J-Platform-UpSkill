/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.Objects;

/**
 * Objecto Habilitação Académica
 * @author Asus
 */
public class HabilitacaoAcademica {

    private static final String PATTERN_NOME = "[a-zA-Z ]+";
    private String grau;
    private String nomeCurso;
    private String faculdade;
    private double media;

    /**
     * Constrói uma instância de Habilitação Académica recebendo os atributos por parâmetro. 
     * Só é criada se todos os parâmetros forem válidos.
     * @param nomeCurso nome do curso
     * @param faculdade nome do estabelecimento de ensino
     * @param grau nível de habilitações académicas
     * @param media média de conclusão do curso
     */
    public HabilitacaoAcademica(String nomeCurso,String faculdade ,String grau, double media) {
        if (validadeData(grau, nomeCurso, faculdade, media)) {
            setGrau(grau);
            setNomeCurso(nomeCurso);
            setFaculdade(faculdade);
            setMedia(media);
        }
    }

    private boolean validadeData(String grau, String nomeCurso, String faculdade, double media) {
        if (grau.isEmpty() || nomeCurso.isEmpty() || faculdade.isEmpty()) {
            throw new IllegalArgumentException("Nenhum dos Argumentos podem estar vazios");
        }
        return true;
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">

    /**
     * Devolve o nível de habilitações académicas.
     * @return nível de habilitações académicas
     */
    public String getGrau() {
        return grau;
    }

    /**
     * Modifica o nível de habilitações académicas.
     * @param grau novo nível de habilitações académicas.
     */
    private void setGrau(String grau) {
        this.grau = grau;
    }

    /**
     * Devolve o nome do curso.
     * @return nome do curso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /**
     * Modifica o nome do curso.
     * @param nomeCurso novo nome do curso
     */
    private void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    /**
     * Devolve o nome do estabelecimento de ensino.
     * @return nome do estabelecimento de ensino
     */
    public String getFaculdade() {
        return faculdade;
    }

    /**
     * Modifica o nome do estabelecimento de ensino.
     * @param faculdade novo nome do estabelecimento de ensino
     */
    private void setFaculdade(String faculdade) {
            this.faculdade = faculdade;
    }

    /**
     * Devolve a média de conclusão do curso.
     * @return média de conclusão do curso
     */
    public double getMedia() {
        return media;
    }

    /**
     * Modifica a média de conclusão do curso.
     * @param media nova média de conclusão do curso
     */
    private void setMedia(double media) {
        if (media < 10) {
            throw new IllegalArgumentException("Media não pode ser negativa");
        } else if (media > 20) {
            throw new IllegalArgumentException("Media não pode ser superior a 20");
        } else {
            this.media = media;
        }
    }
    // </editor-fold>

    /**
     * Compara a Habilitação Académica com o objeto recebido.
     *
     * @param o objeto a comparar com a Habilitação Académica.
     * @return true se o objeto recebido representar um objecto
     * equivalente à Habilitação Academica. Caso contrário, retorna false.
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
        HabilitacaoAcademica obj = (HabilitacaoAcademica) o;
        return (Objects.equals(getGrau(), obj.getGrau())
                && Objects.equals(getNomeCurso(), obj.getNomeCurso())
                && Objects.equals(getFaculdade(), obj.getFaculdade())
                && Objects.equals(getMedia(), obj.getMedia()));
    }

    /**
     * Devolve a descrição textual da Habilitação Académica.
     *
     * @return caraterísticas da Habilitação Académica
     */
    public String toString() {
        return String.format("Grau académico: %s\nNome do curso: %s\nFaculdade: %s\nMedia: %.2f\n", this.grau, this.nomeCurso, this.faculdade, this.media);
    }
}
