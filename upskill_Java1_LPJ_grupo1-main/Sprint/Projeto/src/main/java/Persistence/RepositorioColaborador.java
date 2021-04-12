/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Colaborador;
import com.company.model.Organizacao;
import java.util.List;

/**
 * Interface do Repositório de Colaboradores
 * @author joaor
 */
public interface RepositorioColaborador {

    public List<Colaborador> getColaboradoresByOrg(Organizacao org) throws Exception;

    public List<Colaborador> getAllColaboradores() throws Exception;

    public void save(Colaborador colab) throws Exception;
    
    public Colaborador find(String email) throws Exception;
    
    public Colaborador findGestorByOrg(int nif) throws Exception;
}
