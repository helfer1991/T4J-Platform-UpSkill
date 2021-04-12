/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioCompetenciaTecnica;
import com.company.model.CompetenciaTecnica;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RepositorioCompetenciaTecnicaMemory implements RepositorioCompetenciaTecnica {

    @Override
    public void save(CompetenciaTecnica ct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompetenciaTecnica> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompetenciaTecnica find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompetenciaTecnica getCompetenciaTecnicaByDescBreve(String descricao) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompetenciaTecnica> getListaCompetenciaTecnicaByAreaAtividade(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
