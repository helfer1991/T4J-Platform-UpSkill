/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.CompetenciaTecnica;
import com.company.model.GrauProficiencia;
import java.util.List;

/**
 * Interface do Repositório de Graus de Proficiência
 * @author joaor
 */
public interface RepositorioGrauProficiencia {

    public void save(GrauProficiencia gp) throws Exception;

    public List<GrauProficiencia> getAll() throws Exception;

    public List<GrauProficiencia> getGrausProfByCompTec(CompetenciaTecnica competenciaTecnica) throws Exception;
    
    public GrauProficiencia findGrauProficienciaFromCTByValor(CompetenciaTecnica ct, String valor) throws Exception;
    
}
