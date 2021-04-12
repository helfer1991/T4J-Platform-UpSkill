/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.Anuncio;
import com.company.model.Atribuicao;
import com.company.model.Candidatura;
import com.company.model.Classificacao;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.ProcessoSeriacao;
import com.company.model.TipoRegimento1;
import com.company.model.TipoRegimento2;
import com.company.model.TipoRegimento3;
import com.company.model.TiposRegimentoEnum;
import com.company.model.TiposRegimentoStrategy;
import com.company.utils.Constantes;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Seriações.
 * @author joaor
 */
public class RegistarSeriacaoController {

    private Plataforma plat = Plataforma.getInstance();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    private TiposRegimentoStrategy tRegSt = null;

    private List<Candidatura> listaCand;
    private ArrayList<Classificacao> tmpListaClass;
    private int pos = 1;
    private Anuncio anuncio = null;

    /**
     * Constrói uma instância de RegistarSeriacaoController. Valida o acesso do
     * utilizador ao registo do processo de seriação.
     * 
     * @throws Exception "Utilizador não Autorizado"
     */
    public RegistarSeriacaoController() throws Exception {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        listaCand = new ArrayList<>();
        tmpListaClass = new ArrayList<>();
        pos = 1;
    }

    /**
     * Regista o processo de seriação conforme a estratégia a utilizar.
     * @param participantes participantes de uma organização
     * @param anuncio anúncio referente ao processo de seriação
     * @throws Exception 
     */
    public void RegistaProcessoSeriacao(List<Colaborador> participantes, Anuncio anuncio) throws Exception {
        try {
            if (anuncio.getRegimento().getId() == TiposRegimentoEnum.Tipo1.id) {
                tRegSt = new TipoRegimento1();
                tRegSt.RegistaProcessoSeriacao(anuncio, participantes, tmpListaClass);
            } else if (anuncio.getRegimento().getId() == TiposRegimentoEnum.Tipo2.id) {
                tRegSt = new TipoRegimento2();
                tRegSt.RegistaProcessoSeriacao(anuncio, participantes, tmpListaClass);
            } else {
                tRegSt = new TipoRegimento3();
                tRegSt.RegistaProcessoSeriacao(anuncio, participantes, tmpListaClass);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve uma lista de processos de seriação efectuados.
     * @return lista de processos de seriação efectuados
     * @throws Exception 
     */
    public List<ProcessoSeriacao> getListaSeriados() throws Exception {
        return fabricaRepositorios.getRepositorioProcessoSeriacao().getProcessosSeriacaoByOrgNif(getOrg().getNif());
    }

    /**
     * Devolve uma lista de candidaturas.
     * @return lista de candidaturas
     */
    public List<Candidatura> getListCandidaturas() {
        return listaCand;
    }

    /**
     * Devolve uma lista de classificações.
     * @return lista de classificações.
     */
    public List<Classificacao> getListClassif() {
        return tmpListaClass;
    }

    /**
     * Verifica se a referência da tarefa, no processo de seriação, é igual à do anúncio passada por parêmetro.
     * @param anun anúncio
     * @return true se a referência da tarefa do processo for igual à referência da tarefa do anúncio passado por parâmetro, caso contrário devolve false.
     * @throws Exception 
     */
    public boolean hasProcSeriacao(Anuncio anun) throws Exception {
        boolean flag = false;
        Date now = Date.valueOf(LocalDate.now());
        if (anun.getdInicioSeriacao().after(now) || anun.getdFimSeriacao().before(now) && anun.getColab().getEmail().equalsIgnoreCase(plat.getUsersAPI().getEmail())) {
            flag = true;
        } else {
            if (getListaSeriados().size() == 0) {
                flag = false;
            }
            for (ProcessoSeriacao pS : getListaSeriados()) {
                if (pS.getAnuncio().getTarefa().getRef().equalsIgnoreCase(anun.getTarefa().getRef())) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    /**
     * Modifica a lista local de candidaturas, dependete do anúncio passado por parâmetro.
     * @param anuncio anuncio referente à candidatura
     * @throws Exception 
     */
    public void setListaCand(Anuncio anuncio) throws Exception {
        this.anuncio = anuncio;

        List<Candidatura> tmp = new ArrayList<>();
        for (Candidatura cand : fabricaRepositorios.getRepositorioCandidatura().getByAnuncio(anuncio)) {
            Anuncio a = cand.getAnuncio();
            if (cand.getAnuncio().equals(anuncio)) {
                tmp.add(cand);
            }
        }
        listaCand.addAll(tmp);
    }

    /**
     * Adiciona uma candidatura à lista local de candidaturas.
     * @param cand candidatura
     */
    public void addCandToList(Candidatura cand) {
        LocalDate now = LocalDate.now();
        Date dNow = Date.valueOf(now);
        Classificacao classif = new Classificacao(pos++, dNow, cand);
        this.tmpListaClass.add(classif);
        this.listaCand.remove(cand);
    }

    /**
     * Devolve a posição da classificação.
     * @return posição da classificação
     */
    public int getPos() {
        return pos;
    }

    /**
     * Remove a classificação da lista local.
     * @param classif classificação
     */
    public void removeClassFromList(Classificacao classif) {
        this.listaCand.add(classif.getCandidatura());
        this.tmpListaClass.remove(classif);
        pos--;
    }

    /**
     * Devolve a organização a que pertence o utilizador de login.
     * @return organização a que pertence o utilizador de login
     * @throws Exception 
     */
    public Organizacao getOrg() throws Exception {
        return fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(this.plat.getUsersAPI().getEmail());
    }

    /**
     * Devolve uma lista de colaboradores da organização.
     * @return lista de colaboradores da organização
     * @throws Exception 
     */
    public List<Colaborador> getColaboradoresByOrg() throws Exception {
        return fabricaRepositorios.getRepositorioColaborador().getColaboradoresByOrg(getOrg());
    }

    /**
     * Devolve uma lista de classificações, filtrada pelo processo de seriação selecionado.
     * @param selectedItem processo de seriação selecionado
     * @return lista de classificações
     */
    public List<Classificacao> getClassificacaoByProccSer(ProcessoSeriacao selectedItem) {
        return selectedItem.getClassificacoes();
    }

    /**
     * Valida se o processo de seriação já terminou, se já foi atribuído a tarefa a um Freelancer.
     * @param pS processo de seriação
     * @return true se a tarefa já tiver sido atribuída, caso contrário devolve false
     * @throws Exception 
     */
    public boolean triggerBtnAddAtribuicao(ProcessoSeriacao pS) throws Exception {
        if (plat.getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            if (!hasAtribuicao(pS)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o processo de seriação, passado por parâmetro, tem a atribuição feita.
     * 
     * @param pS processo de seriação
     * @return true se processo de seriação tiver a atribuição feita, caso contrário devolve false
     * @throws Exception 
     */
    public boolean hasAtribuicao(ProcessoSeriacao pS) throws Exception {

        if (pS.getAnuncio().getRegimento().getId() == TiposRegimentoEnum.Tipo1.id) {
            for (Atribuicao at : fabricaRepositorios.getRepositorioAtribuicao().getAllByOrg(getOrg())) {
                if (pS.getAnuncio().getTarefa().getRef().equalsIgnoreCase(at.getPickedClass().getCandidatura().getAnuncio().getTarefa().getRef())) {
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }

}
