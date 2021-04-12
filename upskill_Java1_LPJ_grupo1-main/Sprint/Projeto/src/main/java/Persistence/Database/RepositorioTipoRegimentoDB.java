/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.RepositorioTipoRegimento;
import com.company.data.DataHandler;
import com.company.model.TipoRegimento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioTipoRegimento.
 * @author joaor
 */
public class RepositorioTipoRegimentoDB extends DataHandler implements RepositorioTipoRegimento {

    /**
     * Método que grava os atributos de um tipo de regimento na respectiva base de dados.
     * @param tp tipo de regimento
     */
    @Override
    public void save(TipoRegimento tp) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = getConnection();

            cs = conn.prepareCall("{call addTipoRegimento(?,?)}");

            cs.setString(1, tp.getDesignacao());
            cs.setString(2, tp.getDescricaoRegras());
            cs.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve um tipo de regimento existente na base de dados, que tenha a designação passada por parâmetro 
     * @param designacao designação do tipo de regimento
     * @return tipo de regimento
     * @throws SQLException "Erro em findTiposRegimento"
     */
    @Override
    public TipoRegimento find(String designacao) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getTipoRegimentoByDesig(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, designacao);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            rs.next();

            TipoRegimento tReg = null;

            tReg = new TipoRegimento(rs.getString("DESIGNACAO"), rs.getString("DESCRICAO"));
            tReg.setId(rs.getInt("ID"));

            return tReg;

        } catch (SQLException e) {
            throw new SQLException("Erro em findTiposRegimento" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    /**
     * Devolve uma lista de tipos de regimento a partir da tabela de TipoRegimento da base de dados.
     * @return lista de tipos de regimento
     * @throws SQLException "Erro em getAllTiposRegimento"
     */
    @Override
    public List<TipoRegimento> getAll() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getTiposRegimento}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            return montarListaTipoRegimento(resultSet);

        } catch (SQLException e) {
            throw new SQLException("Erro em getAllTiposRegimento" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private List<TipoRegimento> montarListaTipoRegimento(ResultSet rs) throws SQLException {
        List<TipoRegimento> ltipoRegimento = new ArrayList<TipoRegimento>();
        try {
            while (rs.next()) {
                TipoRegimento tReg = new TipoRegimento(rs.getString("DESIGNACAO"), rs.getString("DESCRICAO"));
                tReg.setId(rs.getInt("ID"));
                ltipoRegimento.add(montarTipoRegimento(rs));
            }
        } catch (Exception e) {
            throw new SQLException("Erro em montarListaTipoRegimento" + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return ltipoRegimento;
    }

    private TipoRegimento montarTipoRegimento(ResultSet rs) throws SQLException {
        TipoRegimento tReg = null;

        try {
            tReg = new TipoRegimento(rs.getString("DESIGNACAO"), rs.getString("DESCRICAO"));
            tReg.setId(rs.getInt("ID"));
        } catch (Exception e) {
            throw new SQLException("Erro em montarTipoRegimento" + e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
        return tReg;
    }

    /**
     * Devolve um tipo de regimento existente na base de dados, que tenha o id do tipo de regimento passado por parâmetro.
     * @param tipoRegimentoID identificação do tipo de regimento
     * @return tipo de regimento
     * @throws Exception "Erro em findTiposRegimento"
     */
    @Override
    public TipoRegimento findById(int tipoRegimentoID) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getTipoRegimentoById(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, tipoRegimentoID);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();

            return montarTipoRegimento(resultSet);

        } catch (SQLException e) {
            throw new SQLException("Erro em findTiposRegimento" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }
}
