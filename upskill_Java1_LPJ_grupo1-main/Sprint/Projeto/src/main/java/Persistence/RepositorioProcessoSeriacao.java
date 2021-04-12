/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Classificacao;
import com.company.model.ProcessoSeriacao;
import java.util.List;

/**
 * Interface do Repositório de Processos de Seriação
 * @author joaor
 */
public interface RepositorioProcessoSeriacao {
    
    public void save (ProcessoSeriacao pS) throws Exception;

    public List<Classificacao> getListClassificacoesByProcSerId(String id) throws Exception;

    public List<ProcessoSeriacao> getProcessosSeriacaoByOrgNif(String nif) throws Exception;
}
