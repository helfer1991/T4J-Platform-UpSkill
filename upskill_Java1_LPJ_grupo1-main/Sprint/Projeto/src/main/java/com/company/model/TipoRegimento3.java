/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Regra do Tipo de Regimento 3 que implementa TiposRegimentoStrategy
 * @author joaor
 */
public class TipoRegimento3 implements TiposRegimentoStrategy {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    private Plataforma plat = Plataforma.getInstance();

    /**
     * Regista o processo de seriação automática com base no segundo preço mais baixo e atribuição obrigatória
     * @param anuncio anúncio referente ao processo de atribuição
     * @param participantes participantes, colaboradores de uma organização
     * @param listaClass lista de classificações
     * @throws Exception 
     */
    @Override
    public void RegistaProcessoSeriacao(Anuncio anuncio, List<Colaborador> participantes, ArrayList<Classificacao> listaClass) throws Exception {

        int pos = 1;

        List<Candidatura> listaCand = new ArrayList<>();
        for (Candidatura cand : fabricaRepositorios.getRepositorioCandidatura().getByAnuncio(anuncio)) {
            Anuncio a = cand.getAnuncio();
            if (cand.getAnuncio().equals(anuncio)) {
                listaCand.add(cand);
            }
        }

        LocalDate now = LocalDate.now();
        Date dNow = Date.valueOf(now);
        Collections.sort(listaCand);

        for (Candidatura cand : listaCand) {
            Classificacao classif = new Classificacao(pos++, dNow, cand);
            listaClass.add(classif);
        }

        ProcessoSeriacao pS = new ProcessoSeriacao(dNow, fabricaRepositorios.getRepositorioColaborador().find(this.plat.getUsersAPI().getEmail()),
                anuncio, participantes, listaClass);

        fabricaRepositorios.getRepositorioProcessoSeriacao().save(pS);
        RegistaAtribuicao(pS.getClassificacoes().get(1));
    }

    /**
     * Regista o processo de atribuição na base de dados
     * @param classif classificação
     * @throws Exception 
     */
    @Override
    public void RegistaAtribuicao(Classificacao classif) throws Exception {
        Atribuicao at = new Atribuicao(classif);
        fabricaRepositorios.getRepositorioAtribuicao().save(at);
    }

}
