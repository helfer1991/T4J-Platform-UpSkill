/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.Atribuicao;
import com.company.model.Classificacao;
import com.company.model.Plataforma;
import com.company.model.ProcessoSeriacao;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Atribuições.
 * @author joaor
 */
public class RegistarAtribuicaoController {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    Plataforma plat = Plataforma.getInstance();

    /**
     * Constrói uma instância de RegistarAtribuicaoController.
     */
    public RegistarAtribuicaoController() {

    }

    /**
     * Devolve uma lista de atribuições.
     * @return lista de atribuições
     * @throws Exception 
     */
    public List<Atribuicao> getListAtribuicoes() throws Exception {
        return fabricaRepositorios.getRepositorioAtribuicao().getAllByOrg(fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(plat.getUsersAPI().getEmail()));
    }

    /**
     * Adiciona uma atribuição ao repositório.
     * @param classif classificação
     * @throws Exception 
     */
    public void addAtribuicao(Classificacao classif) throws Exception {
        try {
            Atribuicao at = new Atribuicao(classif);
            fabricaRepositorios.getRepositorioAtribuicao().save(at);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Devolve uma lista de classificações do anúncio selecionado, filtrada pelo processo de seriação.
     * @param pS processo de seriação
     * @return lista de classificações do anúncio selecionado
     * @throws Exception 
     */
    public List<Classificacao> getListaClassificacoesSelectedAnuncio(ProcessoSeriacao pS) throws Exception{
        return fabricaRepositorios.getRepositorioProcessoSeriacao().getListClassificacoesByProcSerId(pS.getAnuncio().getTarefa().getRef());
    }

}
