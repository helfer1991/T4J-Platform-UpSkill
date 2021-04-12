/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioGrauProficiencia;
import com.company.data.DataHandler;
import com.company.model.CompetenciaTecnica;
import com.company.model.GrauProficiencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa
 * o RepositorioGrauProficiencia
 *
 * @author joaor
 */
public class RepositorioGrauProficienciaDB extends DataHandler implements RepositorioGrauProficiencia {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de um grau de proficiência na respectiva
     * base de dados.
     *
     * @param gp grau de proficiência
     */
    @Override
    public void save(GrauProficiencia gp) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{call addGrauProficiencia(?,?,?)}");

            cs.setString(1, gp.getValor());
            cs.setString(2, gp.getDesignacao());
            cs.setString(3, gp.getCompT().getId());

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
     * Devolve uma lista de graus de proficiência a partir da tabela
     * GrauProficiencia da base de dados.
     *
     * @return lista de graus de proficiência
     * @throws Exception "Error at getAllGrausProf"
     */
    @Override
    public List<GrauProficiencia> getAll() throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        GrauProficiencia gp = null;
        List<GrauProficiencia> tmp = new ArrayList<GrauProficiencia>();
        try {

            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getGrausProficiencia()}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            while (resultSet.next()) {
                gp = new GrauProficiencia(resultSet.getString("valor"), resultSet.getString("designacao"), fabricaRepositorios.getRepositorioCompetenciaTecnica().find(resultSet.getString("codCompetenciaTecnica")));

                tmp.add(gp);
            }

            return tmp;

        } catch (SQLException e) {
            throw new SQLException("Error at getAllGrausProf" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve uma lista de graus de proficiência, filtrada pelo id da
     * competência técnica.
     *
     * @param competenciaTecnica competência técnica
     * @return lista de graus de proficiência da competência técnica
     * @throws Exception "Error at getGrausProfByCompTec"
     */
    @Override
    public List<GrauProficiencia> getGrausProfByCompTec(CompetenciaTecnica competenciaTecnica) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getGrausProficienciaByCompTecnica(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, competenciaTecnica.getId());
            cs.execute();
            resultSet = (ResultSet) cs.getObject(1);

            return montarListaGrausProf(resultSet, competenciaTecnica);

        } catch (SQLException e) {
            throw new SQLException("Error at getGrausProfByCompTec" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private List<GrauProficiencia> montarListaGrausProf(ResultSet rs, CompetenciaTecnica competenciaTecnica) throws Exception {
        GrauProficiencia gp = null;
        List<GrauProficiencia> tmp = new ArrayList<GrauProficiencia>();
        try {
            while (rs.next()) {
                gp = new GrauProficiencia(rs.getString("valor"), rs.getString("designacao"), competenciaTecnica);

                tmp.add(gp);
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

    /**
     * Devolve um grau de proficiência existente na base de dados, que esteja
     * associado a uma competência técnica e um respectivo valor passado por
     * parâmetro
     *
     * @param ct competência técnica
     * @param valor valor do grau de proficiência
     * @return grau de proficiência
     * @throws Exception "Error at getGrausProfByCompTec"
     */
    @Override
    public GrauProficiencia findGrauProficienciaFromCTByValor(CompetenciaTecnica ct, String valor) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getGrausProficienciaByCompTecnicaAndValor(?,?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, ct.getId());
            cs.setString(3, valor);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();
            return montarGrauProficiencia(resultSet);

        } catch (SQLException e) {
            throw new SQLException("Error at getGrausProfByCompTec" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

    }

    private GrauProficiencia montarGrauProficiencia(ResultSet rs) throws Exception {
        GrauProficiencia gp = null;
        try {
            gp = new GrauProficiencia(rs.getString("valor"), rs.getString("designacao"),
                    fabricaRepositorios.getRepositorioCompetenciaTecnica().find(rs.getString("codCompetenciaTecnica")));

        } catch (SQLException e) {
            throw new SQLException("Error at montarGrauProficiencia" + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return gp;
    }

}
