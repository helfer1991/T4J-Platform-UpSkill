/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

import Persistence.RepositorioProcessoSeriacao;
import com.company.model.Classificacao;
import com.company.model.ProcessoSeriacao;
import java.util.List;

/**
 *
 * @author joaor
 */
public class RepositorioProcessoSeriacaoMemory implements RepositorioProcessoSeriacao{

    @Override
    public List<Classificacao> getListClassificacoesByProcSerId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProcessoSeriacao> getProcessosSeriacaoByOrgNif(String nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(ProcessoSeriacao pS){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
