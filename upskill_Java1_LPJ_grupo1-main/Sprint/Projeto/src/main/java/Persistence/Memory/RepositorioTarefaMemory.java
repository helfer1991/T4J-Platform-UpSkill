/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioTarefa;
import com.company.model.Organizacao;
import com.company.model.Tarefa;
import java.util.List;

/**
 *
 * @author diana
 */
public class RepositorioTarefaMemory implements RepositorioTarefa {

     private List<Tarefa> lTarefa;
    
    @Override
    public void save(Tarefa tarefa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tarefa find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tarefa> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tarefa> getListaTarefasByOrg(Organizacao org) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
