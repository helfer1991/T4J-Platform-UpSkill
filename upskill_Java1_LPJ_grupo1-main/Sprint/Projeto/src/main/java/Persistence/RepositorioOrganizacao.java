/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Organizacao;
import java.util.List;

/**
 * Interface do Repositório de Organizações
 * @author Asus
 */
public interface RepositorioOrganizacao {
    
    public void save(Organizacao rg) throws Exception;
    
    public Organizacao find(String nif) throws Exception;
    
    public List<Organizacao> getAll() throws Exception;

    public Organizacao findByColabEmail(String string) throws Exception;
}
