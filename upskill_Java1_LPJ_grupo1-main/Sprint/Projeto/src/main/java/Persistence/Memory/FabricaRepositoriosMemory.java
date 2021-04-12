/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioAnuncio;
import Persistence.RepositorioAreaAtividade;
import Persistence.RepositorioAtribuicao;
import Persistence.RepositorioCandidatura;
import Persistence.RepositorioCategoriaTarefa;
import Persistence.RepositorioColaborador;
import Persistence.RepositorioCompetenciaTecnica;
import Persistence.RepositorioFreelancer;
import Persistence.RepositorioGrauProficiencia;
import Persistence.RepositorioOrganizacao;
import Persistence.RepositorioProcessoSeriacao;
import Persistence.RepositorioTarefa;
import Persistence.RepositorioTipoRegimento;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class FabricaRepositoriosMemory implements FabricaRepositorios {

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException {
        return new RepositorioOrganizacaoMemory();
    }

    @Override
    public RepositorioAreaAtividade getRepositorioAreaAtividade() throws SQLException {
        return new RepositorioAreaAtividadeMemory();
    }

    @Override
    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() throws SQLException {
        return new RepositorioCategoriaTarefaMemory();
    }

    @Override
    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() throws SQLException {
        return new RepositorioCompetenciaTecnicaMemory();
    }

    @Override
    public RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws SQLException {
        return new RepositorioGrauProficienciaMemory();
    }

    @Override
    public RepositorioColaborador getRepositorioColaborador() throws SQLException {
        return new RepositorioColaboradorMemory();
    }

    @Override
    public RepositorioTarefa getRepositorioTarefa() throws SQLException {
        return new RepositorioTarefaMemory();
    }

    @Override
    public RepositorioFreelancer getRepositorioFreelancer() throws SQLException {
        return new RepositorioFreelancerMemory();
    }

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() throws SQLException {
        return new RepositorioAnuncioMemory();
    }

    @Override
    public RepositorioCandidatura getRepositorioCandidatura() throws SQLException {
        return new RepositorioCandidaturaMemory();
    }

    @Override
    public RepositorioProcessoSeriacao getRepositorioProcessoSeriacao() throws SQLException {
        return new RepositorioProcessoSeriacaoMemory();
    }

    @Override
    public RepositorioTipoRegimento getRepositorioTipoRegimento() throws SQLException {
        return new RepositorioTipoRegimentoMemory();
    }

    @Override
    public RepositorioAtribuicao getRepositorioAtribuicao() throws Exception {
        return new RepositorioAtribuicaoMemory();
    }
    
}
