/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.utils.Constantes;
import com.company.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para
 * registar novas Categorias de Tarefa.
 *
 * @author diana
 */
public class RegistarCategoriaTarefaController {

    private final Plataforma plat = Plataforma.getInstance();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    private List<CaraterCT> lcompTec;
    
    /**
     * Constrói uma instância de DefinirCategoriaTarefaController. Valida o
     * acesso do utilizador 'Administrador' para efectuar o registo de
     * categorias de tarefa.
     */
    public RegistarCategoriaTarefaController() throws Exception {
        if (!plat.getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_ADMINISTRADOR)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.lcompTec = new ArrayList<>();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Categoria de Tarefa construído
     * com os parâmetros de entrada.
     *
     * @param descricao descrição da categoria de tarefa
     * @param oArea área de atividade na qual se enquadra a categoria de tarefa
     * @param lcompTec lista de competências técnicas to tipo List, da área de
     * atividade recebida
     * @return true se for possível adicionar a categoria de tarefa, caso
     * contrário devolve false
     */
    public boolean registaCategoriaTarefa(String descricao, AreaAtividade oArea, List<CaraterCT> lcompTec) throws Exception {
        boolean saved = false;
        try {
            CategoriaTarefa categoria = new CategoriaTarefa(descricao, oArea, lcompTec);
            fabricaRepositorios.getRepositorioCategoriaTarefa().Save(categoria);
            saved = true;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return saved;
    }

    /**
     * Adiciona a competência técnica com a respectiva obrigatoriedade e grau de
     * proficiencia à lista de competências técnicas.
     *
     * @param ct competência técnica a adicionar
     * @param obrigatoriedade obrigatoriedade da competência técnica
     * @param grauProficiencia grau de proficiencia associado à competência
     * técnica
     */
    public void addCompTecnicaToList(CompetenciaTecnica ct, int obrigatoriedade, String grauProficiencia) {
        CaraterCT caraterct = new CaraterCT(ct.getId(), obrigatoriedade, grauProficiencia);
        this.lcompTec.add(caraterct);
    }

    /**
     * Remove a competência técnica, da lista de competências, passada por
     * parâmetro.
     *
     * @param ct competência técnica da lista de competências
     */
    public void RemoveCompTecnicaFromList(CompetenciaTecnica ct) {
        this.lcompTec.remove(ct);
    }

    /**
     * Devolve a lista de competências técnicas com respectiva obrigatoriedade.
     *
     * @return lista de competências técnicas com respectiva obrigatoriedade
     */
    public List<CaraterCT> getListaCaraterCTCtrl() {
        return this.lcompTec;
    }

    /**
     * Devolve uma lista de áreas de atividade.
     *
     * @return lista de áreas de atividade
     */
    public List<AreaAtividade> getListaAreasAtividade() throws Exception {
        return fabricaRepositorios.getRepositorioAreaAtividade().getAll();
    }
    
    /**
     * Devolve uma área de atividade, filtrada por id.
     * @param id identificação da área de atividade
     * @return área de atividade
     * @throws Exception 
     */
    public AreaAtividade getAreaAtividade(String id) throws Exception {
        return fabricaRepositorios.getRepositorioAreaAtividade().find(id);
    }
    
    /**
     * Devolve uma lista de competências técnicas.
     *
     * @return lista de competências técnicas
     */
    public List<CompetenciaTecnica> getListaCompetenciasTecnicas() throws Exception {
        return fabricaRepositorios.getRepositorioCompetenciaTecnica().getAll();

    }

    /**
     * Devolve uma lista de categorias de tarefa.
     *
     * @return lista de categorias de tarefa
     */
    public List<CategoriaTarefa> getListaCatTarefa() throws Exception {
        List<CategoriaTarefa> listaCategorias = fabricaRepositorios.getRepositorioCategoriaTarefa().getAll();
        return listaCategorias;
    }
    
    /**
     * Devolve a lista de descrições e respectivos id's, de áreas de atividade.
     *
     * @return lista de descrições e respectivos id's, de áreas de atividade, do
     * tipo ArrayList
     */
    public ArrayList<String> getListaAreaAtividadeDescBreve() throws Exception {
        List<AreaAtividade> list = new ArrayList<AreaAtividade>(this.getListaAreasAtividade());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (AreaAtividade aa : list) {
            idDescBreve = "ID: " + aa.getId() + " \n" + "DescBreve: " + aa.getDescBreve();
            listd.add(idDescBreve);
        }

        return listd;
    }

    /**
     * Devolve uma competência técnica, filtrada pela descrição breve.
     * @param descBreve descrição breve
     * @return competência técnica
     * @throws Exception 
     */
    public CompetenciaTecnica getCompetenciaTecnicaByDescBreve(String descBreve) throws Exception {
        try {
        CompetenciaTecnica compTec = fabricaRepositorios.getRepositorioCompetenciaTecnica().getCompetenciaTecnicaByDescBreve(descBreve);
        return compTec;
        }
        catch (Exception ex) {
            throw new Exception(ex);  
        }
        
    }

    /**
     * Devolve uma lista de competências técnicas, filtrada pelo id da área de atividade.
     * @param idAreaAtividade identificação da área de atividade
     * @return lista de competências técnicas
     * @throws Exception 
     */
    public List<String> getListaCompetenciasTecnicasByIdAreaAtividade(String idAreaAtividade)throws Exception {
        List<String> lista = new ArrayList<>();
        for (CompetenciaTecnica ct : fabricaRepositorios.getRepositorioCompetenciaTecnica().getListaCompetenciaTecnicaByAreaAtividade(idAreaAtividade)) {
            lista.add(ct.getDescBreve());
        }
        return lista;
    }
    
    /**
     * Devolve uma lista de competências técnicas e respectivas
     * obrigatoriedades, de uma categoria de tarefa passada por parâmetro.
     *
     * @param selectedItem categoria de tarefa passada por parâmetro
     * @return lista de competências técnicas e respectivas obrigatoriedades
     */
    public List<CaraterCT> getListCaraterCT(CategoriaTarefa selectedItem) throws Exception {
        String idCatTar = selectedItem.getId().split("-")[1];
        return fabricaRepositorios.getRepositorioCategoriaTarefa().getCaraterCTByCatTarID(idCatTar);
    }

}
