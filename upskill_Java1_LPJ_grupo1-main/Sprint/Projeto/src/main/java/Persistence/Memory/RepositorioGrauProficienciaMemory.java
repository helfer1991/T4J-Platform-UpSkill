/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioGrauProficiencia;
import com.company.model.CompetenciaTecnica;
import com.company.model.GrauProficiencia;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RepositorioGrauProficienciaMemory implements RepositorioGrauProficiencia {

    @Override
    public void save(GrauProficiencia gp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GrauProficiencia> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<GrauProficiencia> getGrausProfByCompTec(CompetenciaTecnica competenciaTecnica) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrauProficiencia findGrauProficienciaFromCTByValor(CompetenciaTecnica ct, String valor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
