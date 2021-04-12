/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.CompetenciaTecnica;
import com.company.model.EnderecoPostal;
import com.company.model.Freelancer;
import com.company.model.GrauProficiencia;
import com.company.model.HabilitacaoAcademica;
import com.company.model.Plataforma;
import com.company.model.ReconhecimentoCT;
import com.company.utils.Constantes;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novos Freelancers.
 * @author joaor
 */
public class RegistarFreelancerController {

    private Plataforma plat;
    private ArrayList<HabilitacaoAcademica> lHabAc;
    private ArrayList<ReconhecimentoCT> lRecFree;
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    private Freelancer free;
    private CompetenciaTecnica ct;

    public RegistarFreelancerController() {
        this.plat = Plataforma.getInstance();
        this.lHabAc = new ArrayList<>();
        this.lRecFree = new ArrayList<>();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Freelancer construído com os parâmetros
     * de entrada.
     * 
     * @param nome nome do Freelancer
     * @param nif número de identificação fiscal do Freelancer
     * @param email email do Freelancer
     * @param telefone contacto telefónico do Freelancer
     * @param morada morada do Freelancer
     * @param codPostal código postal do Freelancer
     * @param localidade localidade da morada do Freelancer
     * @throws Exception 
     */
    public void RegistaFreelancer(String nome, String nif, String email, String telefone, String morada, String codPostal, String localidade) throws Exception {
        EnderecoPostal endPostal = new EnderecoPostal(morada, codPostal, localidade);
        free = new Freelancer(nome, nif, email, telefone, endPostal, lHabAc, lRecFree);
        registaFreelancerAPI(Constantes.ROLE_FREELANCER, free);
        fabricaRepositorios.getRepositorioFreelancer().save(free);
    }

    private boolean registaFreelancerAPI(String funcao, Freelancer f) {
        return plat.getUsersAPI().registerUserWithRoles(f.getNome(), f.getEmail(), f.getTelefone(), plat.getAlgoritmoGeradorPwd().geraPassword(), funcao);
    }

    /**
     * Devolve uma lista de Freelancers.
     * @return lista de Freelancers
     * @throws Exception 
     */
    public List<Freelancer> getListFreelancers() throws Exception {
        return fabricaRepositorios.getRepositorioFreelancer().getAll();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Habilitação Académica construído com os parâmetros
     * de entrada.
     * 
     * @param nomeCurso nome do curso
     * @param faculdade nome do estabelecimento de ensino
     * @param grau nível de habilitações académicas
     * @param media média de conclusão do curso
     * @return true se conseguir adicionar, caso contrário devolve false
     * @throws Exception 
     */
    public boolean RegistaHabilitacaoAcademica(String nomeCurso, String faculdade, String grau, String media) throws Exception {
        boolean flag = false;
        HabilitacaoAcademica hAc = null;
        try {
            hAc = new HabilitacaoAcademica(nomeCurso, faculdade, grau, Double.valueOf(media));
        } catch (Exception e) {
            throw new Exception(e);
        }
        return lHabAc.add(hAc);
    }

    /**
     * Valida e adiciona um novo objecto do tipo Reconhecimento de Competência Técnica construído com os parâmetros
     * de entrada.
     * 
     * @param dataReconhecimento data de reconhecimento da competência técnica
     * @param grau grau de reconhecimento da competência técnica
     */
    public void RegistaReconhecimentoCT(Date dataReconhecimento, GrauProficiencia grau) {
        ReconhecimentoCT rCT = null;
        try {
            rCT = new ReconhecimentoCT(dataReconhecimento, grau);
            lRecFree.add(rCT);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Devolve a lista de descrições e respectivos id's, de competências técnicas.
     *
     * @return lista de descrições e respectivos id's, de competências técnicas, do
     * tipo ArrayList
     */
    public ArrayList<String> getListaCompTecnicaIdDescBreve() throws Exception {

        String idDescBreve = "";
        ArrayList<String> list = new ArrayList<>();
        for (CompetenciaTecnica ct : getCompetenciasTecnicas()) {
            idDescBreve = "ID: " + ct.getId() + " \n" + "DescBreve: " + ct.getDescBreve();
            list.add(idDescBreve);
        }

        return list;
    }

    /**
     * Devolve a lista de designações e respectivos valores, de graus de proficiência.
     *
     * @return lista de designações e respectivos valores, de graus de proficiência, do
     * tipo ArrayList
     */
    public ArrayList<String> getListaGrauProfValorDesig(CompetenciaTecnica ct) throws Exception {
        List<GrauProficiencia> list = new ArrayList<GrauProficiencia>(getGrausProfSelectedCT(ct));
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (GrauProficiencia gp : list) {
            idDescBreve = "Valor: " + gp.getValor() + " \n" + "Designacao: " + gp.getDesignacao();
            listd.add(idDescBreve);
        }

        return listd;
    }

    /**
     * Devolve uma lista de competências técnica existentes.
     *
     * @return lista de áreas de atividade existentes
     */
    public List<CompetenciaTecnica> getCompetenciasTecnicas() throws Exception {
        return fabricaRepositorios.getRepositorioCompetenciaTecnica().getAll();
    }

    /**
     * Devolve uma lista de graus de proficiência existentes.
     *
     * @return lista de graus de proficiência existentes
     */
    public List<GrauProficiencia> getGrausProfSelectedCT(CompetenciaTecnica ct) throws Exception {
        return fabricaRepositorios.getRepositorioGrauProficiencia().getGrausProfByCompTec(ct);
    }

    /**
     * Devolve uma competência técnica, filtrada por id.
     * @param id identificação da competência técnica
     * @return competência técnica
     * @throws Exception 
     */
    public CompetenciaTecnica getCompTecnicaByID(String id) throws Exception {
        ct = fabricaRepositorios.getRepositorioCompetenciaTecnica().find(id);
        return ct;
    }

    /**
     * Devolve um grau de proficiência, filtrado pelo valor.
     * @param valor valor do grau de proficiência
     * @return grau de proficiência
     * @throws Exception 
     */
    public GrauProficiencia getSelectedGrau(String valor) throws Exception {
        return fabricaRepositorios.getRepositorioGrauProficiencia().findGrauProficienciaFromCTByValor(ct, valor);
    }

}
