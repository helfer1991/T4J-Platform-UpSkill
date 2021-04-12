/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

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
 * Classe responsável por associar repositórios da base de dados à fábrica de repositórios 
 * @author Asus
 */
public class FabricaRepositoriosDB implements FabricaRepositorios {

    @Override
    public RepositorioOrganizacao getRepositorioOrganizacao() throws SQLException {
        return new RepositorioOrganizacaoDB();
    }

    @Override
    public RepositorioAreaAtividade getRepositorioAreaAtividade() throws SQLException {
        return new RepositorioAreaAtividadeDB();
    }

    @Override
    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() throws SQLException {
        return new RepositorioCategoriaTarefaDB();
    }

    @Override
    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() throws SQLException {
        return new RepositorioCompetenciaTecnicaDB();
    }

    @Override
    public RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws SQLException {
        return new RepositorioGrauProficienciaDB();
    }

    @Override
    public RepositorioColaborador getRepositorioColaborador() throws SQLException {
        return new RepositorioColaboradorDB();
    }

    @Override
    public RepositorioTarefa getRepositorioTarefa() throws SQLException {
        return new RepositorioTarefaDB();
    }

    @Override
    public RepositorioFreelancer getRepositorioFreelancer() throws SQLException {
        return new RepositorioFreelancerDB();
    }

    @Override
    public RepositorioAnuncio getRepositorioAnuncio() throws SQLException {
        return new RepositorioAnuncioDB();
    }

    @Override
    public RepositorioCandidatura getRepositorioCandidatura() throws SQLException {
        return new RepositorioCandidaturaDB();
    }

    @Override
    public RepositorioProcessoSeriacao getRepositorioProcessoSeriacao() throws SQLException {
        return new RepositorioProcessoSeriacaoDB();
    }

    @Override
    public RepositorioTipoRegimento getRepositorioTipoRegimento() throws SQLException {
        return new RepositorioTipoRegimentoDB();
    }

    @Override
    public RepositorioAtribuicao getRepositorioAtribuicao() throws Exception {
        return new RepositorioAtribuicaoDB();
    }

}
