/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Anuncio;
import com.company.model.Organizacao;
import java.util.List;

/**
 * Interface do Repositório de Anúncios
 * @author joaor
 */
public interface RepositorioAnuncio {
    
    public void save(Anuncio anuncio) throws Exception;
    
    public Anuncio find(String referencia) throws Exception;
    
    public List<Anuncio> getAll() throws Exception;
    
    public List<Anuncio> getAnunciosByOrg(Organizacao org) throws Exception;
    
    public List<Anuncio> getAnunciosDisponiveis() throws Exception;
}
