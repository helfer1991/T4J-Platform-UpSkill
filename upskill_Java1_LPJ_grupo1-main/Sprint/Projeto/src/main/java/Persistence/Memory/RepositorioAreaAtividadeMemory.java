/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Memory;

//import Persistence.Database.RepositorioAreaAtividadeDB;
import Persistence.RepositorioAreaAtividade;
import com.company.model.AreaAtividade;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class RepositorioAreaAtividadeMemory implements RepositorioAreaAtividade { //singleton

    @Override
    public void save(AreaAtividade aa) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AreaAtividade> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AreaAtividade find(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
