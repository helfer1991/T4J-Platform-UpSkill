/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioAtribuicao;
import com.company.model.Atribuicao;
import com.company.model.Organizacao;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RepositorioAtribuicaoMemory implements RepositorioAtribuicao {

    @Override
    public void save(Atribuicao at) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Atribuicao find(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Atribuicao> getAllByOrg(Organizacao org) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
