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
 * registar novas Tarefas.
 *
 * @author diana
 */
public class RegistarTarefaController {

    /**
     * Inicializa a plataforma com uma cópia com a mesma assinatura.
     */
    private Plataforma plat = Plataforma.getInstance();

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    private ArrayList<Anuncio> anunciosOrg;

    /**
     * Constrói uma instância de EspecificarTarefaController. Valida o acesso do
     * utilizador ao registar das tarefas.
     */
    public RegistarTarefaController() throws Exception {
        if (!plat.getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !plat.getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        anunciosOrg = new ArrayList<>();

        this.org = fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(this.plat.getUsersAPI().getEmail());
        anunciosOrg.addAll(fabricaRepositorios.getRepositorioAnuncio().getAnunciosByOrg(org));
    }

    /**
     * Valida e adiciona um novo objecto do tipo Tarefa construído com os
     * parâmetros de entrada.
     *
     * @param ref referência da Tarefa
     * @param designacao designação da Tarefa
     * @param descrInformal descrição informal da Tarefa
     * @param descrTecnica descrição técnica da Tarefa
     * @param duracaoEst duração estimada da Tarefa
     * @param custoEst custo estimado da Tarefa
     * @param catTarefa categoria de tarefa em que se vai enquadrar a Tarefa
     * @param colab colaborador que faz o registo da Tarefa
     */
    public void novaTarefa(String ref, String designacao, String descrInformal,
            String descrTecnica, Integer duracaoEst, Double custoEst, CategoriaTarefa catTarefa, Colaborador colab) throws Exception {
        try {
            Tarefa t = new Tarefa(ref, designacao, descrInformal, descrTecnica, duracaoEst, custoEst, catTarefa, colab);
            fabricaRepositorios.getRepositorioTarefa().save(t);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve a lista de descrições e respectivos id's, de categorias de
     * tarefa.
     *
     * @return lista de descrições e respectivos id's, de categorias de tarefa,
     * do tipo ArrayList
     */
    public ArrayList<String> getListaCatTarefaIdDesc() throws Exception {
        List<CategoriaTarefa> list = new ArrayList<CategoriaTarefa>(getAllCatTarefa());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (CategoriaTarefa ct : list) {
            idDescBreve = ct.getId().split("-")[1] + " : " + ct.getDescricao();
            listd.add(idDescBreve);
        }

        return listd;
    }

    /**
     * Devolve o objecto colaborador correspondente ao e-mail de login.
     *
     * @return objecto colaborador correspondente ao e-mail de login
     */
    public Colaborador getColaborador() throws Exception {
        String email = this.plat.getUsersAPI().getEmail();
        return fabricaRepositorios.getRepositorioColaborador().find(email);
    }

    /**
     * Devolve o objecto categoria de tarefa correspondente ao id passado por
     * parâmetro.
     *
     * @param s identificação da categoria de tarefa
     * @return objecto categoria de tarefa
     */
    public CategoriaTarefa getCategoriaTarefa(String s) throws Exception {
        CategoriaTarefa a = fabricaRepositorios.getRepositorioCategoriaTarefa().find(s);
        return a;
    }

    private List<CategoriaTarefa> getAllCatTarefa() throws Exception {
        List<CategoriaTarefa> i = fabricaRepositorios.getRepositorioCategoriaTarefa().getAll();
        return i;
    }

    /**
     * Devolve uma lista de tarefas existentes na organização.
     *
     * @return lista de tarefas existentes na organização
     */
    public List<Tarefa> getListaTarefas() throws Exception {
        List<Tarefa> j = fabricaRepositorios.getRepositorioTarefa().getListaTarefasByOrg(this.org);
        return j;
    }

    /**
     * Verifica se a tarefa passada por parâmetro existe na base de dados.
     * @param tarefa tarefa
     * @return true se a tarefa já existir na base de dados, caso contrário devolve false
     * @throws Exception 
     */
    public boolean hasAnuncio(Tarefa tarefa) throws Exception {
        if (this.anunciosOrg != null && this.anunciosOrg.size() != 0) {
            for (Anuncio anun : this.anunciosOrg) {
                if ((anun.getTarefa().getRef().equalsIgnoreCase(tarefa.getRef()))) {
                    return true;
                }
            }
        }
        if (tarefa.getColab().getEmail().equalsIgnoreCase(getColaborador().getEmail())) {
            return false;
        } else {
            return true;
        }
    }
}
