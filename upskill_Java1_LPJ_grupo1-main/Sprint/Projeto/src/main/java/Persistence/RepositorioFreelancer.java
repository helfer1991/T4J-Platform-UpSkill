/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import com.company.model.Freelancer;
import java.util.List;

/**
 * Interface do Repositório de Freelancers
 * @author joaor
 */
public interface RepositorioFreelancer {

    public List<Freelancer> getAll() throws Exception;

    public void save(Freelancer free) throws Exception;
    
    public Freelancer find(String email) throws Exception;

    public Freelancer findByNif(int aInt) throws Exception;
}
