/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioCompetenciaTecnica;
import com.company.data.DataHandler;
import com.company.model.CompetenciaTecnica;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioCompetenciaTecnica
 * @author joaor
 */
public class RepositorioCompetenciaTecnicaDB extends DataHandler implements RepositorioCompetenciaTecnica {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de uma competência técnica na respectiva base de dados.
     * @param ct competência técnica
     */
    @Override
    public void save(CompetenciaTecnica ct) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            cs = conn.prepareCall("{call addCompetenciaTecnica(?,?,?,?)}");

            cs.setString(1, ct.getId());
            cs.setString(2, ct.getDescBreve());
            cs.setString(3, ct.getDescDetalhada());
            cs.setString(4, ct.getAreaAtividade().getId());

            cs.executeQuery();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            conn.setAutoCommit(true);
        }
    }

    /**
     * Devolve uma lista de competências técnicas a partir da tabela CompetenciaTecnica da base de dados.
     * @return lista de competências técnicas
     * @throws Exception "Error at getAllCompetenciasTecnicas"
     */
    @Override
    public List<CompetenciaTecnica> getAll() throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getCompetenciasTecnicas}");
            
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            return montarListaCompTec(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getAllCompetenciasTecnicas" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }

    /**
     * Devolve uma competência técnica existente na base de dados, que tenha o id passado por parâmetro 
     * @param id identificação da competência técnica
     * @return competência técnica
     * @throws Exception "Error at getCompetenciaTecnica"
     */
    @Override
    public CompetenciaTecnica find(String id) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getCompetenciaTecnica(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, id);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();

            return montarCompetenciaTecnica(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getCompetenciaTecnica" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve uma competência técnica a partir da tabela CompetenciaTecnica da base de dados, filtrada pela descrição passada por parâmetro.
     * @param descricao descrição
     * @return competência técnica
     * @throws Exception "Error at getCompetenciaTecnica"
     */
    @Override
    public CompetenciaTecnica getCompetenciaTecnicaByDescBreve(String descricao) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getCompetenciaTecnicaByDesc(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, descricao);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();

            return montarCompetenciaTecnica(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getCompetenciaTecnica" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve uma lista de competências técnicas filtrada pelo id da área de atividade passado por parâmetro.
     * @param idAreaAtividade identificação da área de atividade
     * @return lista de competências técnicas
     * @throws Exception "Error at getAllCompetenciasTecnicasByAreaAtividade"
     */
    @Override
    public List<CompetenciaTecnica> getListaCompetenciaTecnicaByAreaAtividade(String idAreaAtividade) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getCompetenciasTecnicasByAreaAtividade(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, idAreaAtividade);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            
            return montarListaCompTec(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getAllCompetenciasTecnicasByAreaAtividade" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }

    private CompetenciaTecnica montarCompetenciaTecnica(ResultSet rs) throws SQLException, Exception {
        CompetenciaTecnica ct = null;

        try {
            ct = new CompetenciaTecnica(rs.getString("codUnico"), rs.getString("descBreve"), rs.getString("descDetalhada"),
                        fabricaRepositorios.getRepositorioAreaAtividade().find(rs.getString("idAreaAtividade")));

            ct.setGrausProficiencia(fabricaRepositorios.getRepositorioGrauProficiencia().getGrausProfByCompTec(ct));

            return ct;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
    }

    private List<CompetenciaTecnica> montarListaCompTec(ResultSet rs) throws SQLException, Exception {
        ArrayList<CompetenciaTecnica> tmp = new ArrayList<>();
        CompetenciaTecnica ct = null;
        try {
            while (rs.next()) {
                ct = new CompetenciaTecnica(rs.getString("codUnico"), rs.getString("descBreve"), rs.getString("descDetalhada"),
                        fabricaRepositorios.getRepositorioAreaAtividade().find(rs.getString("idAreaAtividade")));
                ct.setGrausProficiencia(fabricaRepositorios.getRepositorioGrauProficiencia().getGrausProfByCompTec(ct));
                tmp.add(ct);
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return tmp;
    }

}
