/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Organizacao;
import com.company.model.Tarefa;
import java.util.List;

/**
 * Interface do Repositório de Tarefas
 * @author diana
 */
public interface RepositorioTarefa {
    
    public void save(Tarefa tarefa) throws Exception;
    
    public Tarefa find(String id) throws Exception;
    
    public List<Tarefa> getAll() throws Exception;

    public List<Tarefa> getListaTarefasByOrg(Organizacao org) throws Exception;
    
}
