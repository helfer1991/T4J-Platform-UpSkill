/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Atribuicao;
import com.company.model.Organizacao;
import java.util.List;

/**
 * Interface do Repositório de Atribuição
 * @author joaor
 */
public interface RepositorioAtribuicao {
       
    public void save(Atribuicao at) throws Exception;
    
    public Atribuicao find(String id) throws Exception;
    
    public List<Atribuicao> getAllByOrg(Organizacao org) throws Exception;
}
