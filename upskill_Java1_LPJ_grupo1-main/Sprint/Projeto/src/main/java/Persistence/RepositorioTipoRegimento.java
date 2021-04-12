/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.TipoRegimento;
import java.util.List;

/**
 * Interface do Repositório de Tipos de Regimento
 * 
 * @author joaor
 */
public interface RepositorioTipoRegimento {
    
    public void save(TipoRegimento tp) throws Exception;
    
    public TipoRegimento find(String designacao) throws Exception;
    
    public List<TipoRegimento> getAll() throws Exception;

    public TipoRegimento findById(int tipoRegimentoID) throws Exception;
    
}
