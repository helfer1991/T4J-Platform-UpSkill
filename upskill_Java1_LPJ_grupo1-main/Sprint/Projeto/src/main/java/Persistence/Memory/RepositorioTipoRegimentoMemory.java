/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioTipoRegimento;
import com.company.model.TipoRegimento;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RepositorioTipoRegimentoMemory implements RepositorioTipoRegimento {

    @Override
    public void save(TipoRegimento tp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoRegimento find(String designacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoRegimento> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoRegimento findById(int tipoRegimentoID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
