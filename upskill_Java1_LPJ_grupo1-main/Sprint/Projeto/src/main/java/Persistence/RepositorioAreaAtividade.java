/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.AreaAtividade;
import java.util.List;

/**
 * Interface do Repositório de Áreas de Atividade
 * @author Asus
 */
public interface RepositorioAreaAtividade  {
    public void save(AreaAtividade aa)  throws Exception;
    
    public List<AreaAtividade> getAll() throws Exception;
    
    public AreaAtividade find(String id) throws Exception;
}
