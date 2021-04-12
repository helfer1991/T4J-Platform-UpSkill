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
import java.util.List;

/**
 * Regra do Tipo de Regimento 1 que implementa TiposRegimentoStrategy
 * @author joaor
 */
public class TipoRegimento1 implements TiposRegimentoStrategy {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    private Plataforma plat = Plataforma.getInstance();

    /**
     * Regista o processo de seriação com atribuição opcional.
     * @param anuncio anúncio referente ao processo de atribuição
     * @param participantes participantes, colaboradores de uma organização
     * @param listaClass lista de classificações
     * @throws Exception 
     */
    @Override
    public void RegistaProcessoSeriacao(Anuncio anuncio, List<Colaborador> participantes, ArrayList<Classificacao> listaClass) throws Exception {
        LocalDate now = LocalDate.now();
        Date dNow = Date.valueOf(now);
        Colaborador colab = fabricaRepositorios.getRepositorioColaborador().find(this.plat.getUsersAPI().getEmail());
        ProcessoSeriacao pS = new ProcessoSeriacao(dNow, colab, anuncio, participantes, listaClass);
        fabricaRepositorios.getRepositorioProcessoSeriacao().save(pS);
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
