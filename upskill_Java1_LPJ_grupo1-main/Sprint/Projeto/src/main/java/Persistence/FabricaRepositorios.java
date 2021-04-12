/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;


/**
 * Interface de Repositórios
 * @author Asus
 */
public interface FabricaRepositorios {

    public RepositorioOrganizacao getRepositorioOrganizacao() throws Exception;

    public RepositorioAreaAtividade getRepositorioAreaAtividade() throws Exception;

    public RepositorioCategoriaTarefa getRepositorioCategoriaTarefa() throws Exception;

    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() throws Exception;
    
    public RepositorioGrauProficiencia getRepositorioGrauProficiencia() throws Exception;
    
    public RepositorioColaborador getRepositorioColaborador() throws Exception;

    public RepositorioTarefa getRepositorioTarefa() throws Exception;

    public RepositorioFreelancer getRepositorioFreelancer() throws Exception;

    public RepositorioAnuncio getRepositorioAnuncio() throws Exception;

    public RepositorioCandidatura getRepositorioCandidatura() throws Exception;

    public RepositorioProcessoSeriacao getRepositorioProcessoSeriacao() throws Exception;

    public RepositorioTipoRegimento getRepositorioTipoRegimento() throws Exception;
    
    public RepositorioAtribuicao getRepositorioAtribuicao() throws Exception;

}
