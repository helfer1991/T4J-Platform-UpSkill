/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.RepositorioAreaAtividade;
import com.company.data.DataHandler;
import com.company.model.AreaAtividade;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioAreaAtividade
 * @author Asus
 */
public class RepositorioAreaAtividadeDB extends DataHandler implements RepositorioAreaAtividade {

    /**
     * Método que grava os atributos de áreas de atividade na respectiva base de dados.
     * @param aa área de atividade
     */
    @Override
    public void save(AreaAtividade aa) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{call addAreaAtividade(?, ?, ?)}");

            conn.setAutoCommit(false);

            cs.setString(1, aa.getId());
            cs.setString(2, aa.getDescBreve());
            cs.setString(3, aa.getDescDetalhada());

            cs.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            conn.setAutoCommit(true);
        }
    }

    /**
     * Devolve uma lista de áreas de atividade a partir da tabela AreaAtividade da base de dados.
     * @return lista de áreas de atividade
     * @throws SQLException "Error at getAll "
     */
    @Override
    public List<AreaAtividade> getAll() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAreasAtividade}");

            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            return montarListaAreaAtividade(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at getAll " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve uma área de atividade existente na base de dados, que tenha o id passado por parâmetro.
     * @param id identificação da área de atividade
     * @return área de atividade
     * @throws Exception "Error at find"
     */
    @Override
    public AreaAtividade find(String id) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
         ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAreaAtividadeById(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);            
            cs.setString(2, id);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            rs.next();

            return montarAreaAtividade(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at find" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    private List<AreaAtividade> montarListaAreaAtividade(ResultSet rs) throws SQLException {
        List<AreaAtividade> listaAreasAtividade = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAreasAtividade.add(montarAreaAtividade(rs));
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return listaAreasAtividade;
    }

    private AreaAtividade montarAreaAtividade(ResultSet rs) throws SQLException {
        AreaAtividade areaAtividade = null;
        try {
            
            areaAtividade = new AreaAtividade(rs.getString("ID"), rs.getString("DESCBREVE"), rs.getString("DESCDETALHADA"));

            return areaAtividade;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
