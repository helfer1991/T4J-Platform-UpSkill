/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.CompetenciaTecnica;
import java.util.List;

/**
 * Interface do Repositório de Competências Técnicas
 * @author joaor
 */
public interface RepositorioCompetenciaTecnica {
    public void save(CompetenciaTecnica ct) throws Exception;
    
    public List<CompetenciaTecnica> getAll() throws Exception;
    
    public CompetenciaTecnica find(String id) throws Exception;
    
    public CompetenciaTecnica getCompetenciaTecnicaByDescBreve(String descricao) throws Exception;
    
    public List<CompetenciaTecnica> getListaCompetenciaTecnicaByAreaAtividade(String id) throws Exception;
}
