/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.Anuncio;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.Tarefa;
import com.company.model.TipoRegimento;
import com.company.utils.Constantes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novos Anúncios.
 * @author Asus
 */
public class RegistarAnuncioController {

    /**
     * Inicializa a plataforma com uma cópia com a mesma assinatura.
     */
    private Plataforma plat = Plataforma.getInstance();
    
    /**
     * Declaração e inicialização da fábrica de repositórios.
     */
    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    /** 
     * Constrói uma instância de RegistarAnuncioController.
     * @throws Exception "Utilizador não Autorizado"
     */
    public RegistarAnuncioController() throws SQLException, Exception {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.org = fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(plat.getUsersAPI().getEmail());
    }

    /**
     * Valida e adiciona um novo objecto do tipo Anuncio construído com os parâmetros
     * de entrada.
     * 
     * @param dtInicioPublicitacao data de início da publicação do anúncio da tarefa
     * @param dtFimPublicitacao data de fim da publicação do anúncio da tarefa
     * @param dtInicioCandidatura data de início da candidatura ao anúncio da tarefa
     * @param dtFimCandidatura data de fim da candidatura ao anúncio da tarefa
     * @param dtInicioSeriacao data de início da ordenação dos candidatos ao anúncio da tarefa
     * @param dtFimSeriacao data de fim da ordenação dos candidatos ao anúncio da tarefa
     * @param tarefa tarefa a ser publicada
     * @param colab colaborador que publica a tarefa
     * @param regimento tipo de regimento aplicado à ordenação dos candidatos
     * @throws Exception 
     */
    public void RegistaAnuncio(Date dtInicioPublicitacao, Date dtFimPublicitacao, Date dtInicioCandidatura, Date dtFimCandidatura,
        Date dtInicioSeriacao, Date dtFimSeriacao, Tarefa tarefa, Colaborador colab, TipoRegimento regimento) throws Exception {
        try {
            Anuncio a = new Anuncio(dtInicioPublicitacao, dtFimPublicitacao, dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao, tarefa, colab, regimento);
            fabricaRepositorios.getRepositorioAnuncio().save(a);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve uma lista de anúncios.
     * @return lista de anúncios
     * @throws SQLException
     * @throws Exception 
     */
    public List<Anuncio> getAllAnuncios() throws SQLException, Exception {
        return fabricaRepositorios.getRepositorioAnuncio().getAnunciosByOrg(org);
    }

    /**
     * Devolve uma lista de designações dos tipos de regimento.
     * @return lista de designações dos tipos de regimento.
     * @throws SQLException
     * @throws Exception 
     */
    public List<String> getAllTipoRegimentoDesignacao() throws SQLException, Exception {
        List<String> list1 = new ArrayList<>();
        for (TipoRegimento tipoRegimento : fabricaRepositorios.getRepositorioTipoRegimento().getAll()) {
            list1.add(tipoRegimento.getDesignacao());
        }
        return list1;
    }

    /**
     * Devolve um tipo de regimento, filtrado por designação / id.
     * @param designacao designação do tipo de regimento
     * @return tipo de regimento
     * @throws Exception 
     */
    public TipoRegimento getTipoRegimentoId(String designacao) throws Exception {
        return fabricaRepositorios.getRepositorioTipoRegimento().findById(Integer.valueOf(designacao.split(" ")[1].trim()));
    }

    /**
     * Devolve um colaborador correspondente ao email da sessão.
     * @return colaborador da sessão
     * @throws SQLException
     * @throws Exception 
     */
    public Colaborador getColabByEmail() throws SQLException, Exception {
        String email = plat.getUsersAPI().getEmail();

        return fabricaRepositorios.getRepositorioColaborador().find(email);
    }
}
