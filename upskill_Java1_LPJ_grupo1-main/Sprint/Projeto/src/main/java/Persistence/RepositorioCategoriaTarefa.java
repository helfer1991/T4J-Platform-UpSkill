/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.CaraterCT;
import com.company.model.CategoriaTarefa;
import java.util.List;

/**
 * Interface do Repositório de Categorias de Tarefa
 * @author joaor
 */
public interface RepositorioCategoriaTarefa {
    
    public void Save(CategoriaTarefa ct) throws Exception;
    
    public List<CategoriaTarefa> getAll() throws Exception;
    
    public List<CaraterCT> getCaraterCTByCatTarID(String id) throws Exception;
    
    public CategoriaTarefa find(String id) throws Exception;
}
