/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioCategoriaTarefa;
import com.company.model.CaraterCT;
import com.company.model.CategoriaTarefa;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RepositorioCategoriaTarefaMemory implements RepositorioCategoriaTarefa {

    @Override
    public void Save(CategoriaTarefa ct) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategoriaTarefa> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CaraterCT> getCaraterCTByCatTarID(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CategoriaTarefa find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
