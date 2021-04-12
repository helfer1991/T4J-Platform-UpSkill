/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Anuncio;
import com.company.model.Candidatura;
import java.util.List;

/**
 * Interface do Repositório de Candidaturas
 * @author joaor
 */
public interface RepositorioCandidatura  {

    public List<Candidatura> getAll() throws Exception;
    
    public List<Candidatura> getAllCandidaturasByFreelancerEmail(String email) throws Exception;

    public void save(Candidatura cand) throws Exception;

    public Candidatura find(int id) throws Exception;
    
    public void update(Candidatura cand) throws Exception;
    
    public void remove(Candidatura cand) throws Exception;
    
    public List<Candidatura> getByAnuncio(Anuncio anuncio) throws Exception;
    
    public List<Candidatura> getCandidaturasDispByFreelancerEmail(String email) throws Exception;
}
