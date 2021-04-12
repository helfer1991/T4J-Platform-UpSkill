/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.CaraterCT;
import com.company.model.CompetenciaTecnica;
import com.company.model.Freelancer;
import com.company.model.Plataforma;
import com.company.model.ReconhecimentoCT;
import com.company.utils.Constantes;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Candidaturas.
 * @author Asus
 */
public class RegistarCandidaturaController {

    private final Plataforma plat = Plataforma.getInstance();
    private List<Candidatura> listaCand;
    private Freelancer user;
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Constrói uma instância RegistarCandidaturaTarefaController. Valida o
     * acesso do utilizador 'Freelancer' para se candidatar.
     * @throws SQLException
     * @throws Exception 
     */
    public RegistarCandidaturaController() throws SQLException, Exception {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_FREELANCER)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }

        listaCand = new ArrayList<>();
        user = getFreelancerByEmail();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Candidatura construído com os parâmetros
     * de entrada.
     * 
     * @param anun anúncio alvo da candidatura
     * @param valorPretendido valor pretendido 
     * @param nrDias número de dias
     * @param txtApr texto de apresentação
     * @param txtMotiv texto de motivação
     * @param free freelancer
     * @throws Exception 
     */
    public void registarCandidatura(Anuncio anun, double valorPretendido, int nrDias, String txtApr, String txtMotiv,
            Freelancer free) throws Exception {
        try {
            Date dtCandidatura = Date.valueOf(LocalDate.now());
            Candidatura cand = new Candidatura(anun, dtCandidatura, valorPretendido, nrDias, txtApr, txtMotiv, free);
            fabricaRepositorios.getRepositorioCandidatura().save(cand);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve um freelancer correspondente ao email da sessão.
     * @return freelancer da sessão
     * @throws SQLException
     * @throws Exception 
     */
    public Freelancer getFreelancerByEmail() throws SQLException, Exception {
        String email = plat.getUsersAPI().getEmail();

        return fabricaRepositorios.getRepositorioFreelancer().find(email);
    }

    /**
     * Devolve uma lista de candidaturas.
     * @return lista de candidaturas
     * @throws SQLException
     * @throws Exception 
     */
    public List<Candidatura> getAllCandidaturasByFreelancerEmail() throws SQLException, Exception {
        return fabricaRepositorios.getRepositorioCandidatura().getAllCandidaturasByFreelancerEmail(getFreelancerByEmail().getEmail());
    }

    /**
     * Devolve uma lista de candidaturas disponíveis, filtrada por email.
     * @param email email do freelancer
     * @return lista de candidaturas
     * @throws SQLException
     * @throws Exception 
     */
    public List<Candidatura> getCandidaturasDispByFreelancerEmail(String email) throws SQLException, Exception {
        return fabricaRepositorios.getRepositorioCandidatura().getAllCandidaturasByFreelancerEmail(getFreelancerByEmail().getEmail());
    }

    /**
     * Devolve uma lista de anúncios disponiveis.
     * @return lista de anúncios disponiveis
     * @throws SQLException
     * @throws Exception 
     */
    public List<Anuncio> getAllAnunciosDisponiveis() throws SQLException, Exception {
        List<Anuncio> tmp = new ArrayList<>();

        for (Anuncio anun : fabricaRepositorios.getRepositorioAnuncio().getAnunciosDisponiveis()) {
            if (!hasCandidatura(anun)) {
                tmp.add(anun);
            }
        }
        return tmp;
    }

    /**
     * Verifica se o anúncio passado por parâmetro tem candidaturas associadas.
     * @param anun anúncio
     * @return true se tiver candidaturas, caso contrário devolve false.
     * @throws Exception 
     */
    public boolean hasCandidatura(Anuncio anun) throws Exception {
        List<Candidatura> lcand = fabricaRepositorios.getRepositorioCandidatura().getAllCandidaturasByFreelancerEmail(getFreelancerByEmail().getEmail());

        for (Candidatura cand : lcand) {
            if (cand.getFree().getEmail().equalsIgnoreCase(user.getEmail()) && cand.getAnuncio().getTarefa().getRef().equalsIgnoreCase(anun.getTarefa().getRef())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o Freelancer está elegível para se candidatar ao anúncio.
     * @param anun anúncio
     * @return true se o Freelancer possuir pelo menos as competências obrigatórias e que tenha os graus de proficiência mínimos, caso contrário devolve false.
     * @throws SQLException
     * @throws Exception 
     */
    public boolean isFreelancerIlegivel(Anuncio anun) throws SQLException, Exception {
        int min = 0, rec = 0;
        boolean flag = true;
        List<CaraterCT> ctAnun = new ArrayList<>();
        Freelancer free = null;

        for (CaraterCT cCT : anun.getTarefa().getCatTarefa().getLcompTec()) {
            if (cCT.getObrigatorio() == 1) {
                ctAnun.add(cCT);
            }
        }

        for (CaraterCT cCT : ctAnun) {
            CompetenciaTecnica ct = fabricaRepositorios.getRepositorioCompetenciaTecnica().find(cCT.getCodCompetenciaTecnica());
            min = ct.getListaGrausProficiencia().indexOf(fabricaRepositorios.getRepositorioGrauProficiencia().findGrauProficienciaFromCTByValor(ct, cCT.getGrauMinimoProficiencia()));
            free = fabricaRepositorios.getRepositorioFreelancer().find(plat.getUsersAPI().getEmail());
            for (ReconhecimentoCT rCT : free.getlRecFree()) {
                if (rCT.getGrauReconhecido().getCompT().getId().equalsIgnoreCase(cCT.getCodCompetenciaTecnica())) {
                    rec = ct.getListaGrausProficiencia().indexOf(fabricaRepositorios.getRepositorioGrauProficiencia().findGrauProficienciaFromCTByValor(rCT.getGrauReconhecido().getCompT(), rCT.getGrauReconhecido().getValor()));
                    if (rec >= min) {
                        flag = true;
                    }
                } else {
                    flag = false;
                }
            }
            if (flag == false) {
                break;
            }
        }
        return flag;
    }

    /**
     * Altera os parâmetros de uma candidatura existente.
     * @param cand candidatura
     * @param valor valor pedido
     * @param tempExec tempo de execução
     * @param txtApres texto de apresentação
     * @param txtMotiv texto de motivação
     * @throws Exception 
     */
    public void alterarCandidatura(Candidatura cand, Double valor, int tempExec, String txtApres, String txtMotiv) throws Exception {
        try {
            cand.setValorPretendido(valor);
            cand.setNrDias(tempExec);
            cand.setTxtApr(txtApres);
            cand.setTxtMotiv(txtMotiv);
            fabricaRepositorios.getRepositorioCandidatura().update(cand);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Remove uma candidatura e respectivos parâmetros.
     * @param cand candidatura
     * @throws Exception 
     */
    public void removerCandidatura(Candidatura cand) throws Exception {
        try {
            fabricaRepositorios.getRepositorioCandidatura().remove(cand);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Verifica se a candidatura teve início antes da data e hora actual, e se o fim possuí uma hora e data posteriores.
     * @param selected candidatura selecionada
     * @return true se a candidatura teve início antes da data e hora actual, e se o fim possuí uma hora e data posteriores, caso contrário devolve false.
     */
    public boolean checkDate(Candidatura selected) {
        Date now = Date.valueOf(LocalDate.now());
        if (selected.getAnuncio().getdInicioCandidatura().before(now) && selected.getAnuncio().getdFimCandidatura().after(now)) {
            return true;
        }
        return false;
    }

}
