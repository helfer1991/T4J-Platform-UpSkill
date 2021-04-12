/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioTarefa;
import com.company.data.DataHandler;
import com.company.model.Organizacao;
import com.company.model.Tarefa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa
 * o RepositorioTarefa.
 *
 * @author diana
 */
public class RepositorioTarefaDB extends DataHandler implements RepositorioTarefa {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de uma tarefa na respectiva base de dados.
     *
     * @param tarefa tarefa
     * @throws SQLException "Error at saveTarefa"
     */
    @Override
    public void save(Tarefa tarefa) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        CallableStatement cs1 = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            cs = conn.prepareCall("{ ? = call getColaborador(?)}"); // CHECKAR
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, tarefa.getColab().getEmail());
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();

            int nifOrganizacao = resultSet.getInt("NifOrganizacao");

            //insere uma tarefa na tabela
            cs1 = conn.prepareCall("{call addTarefa(?,?,?,?,?,?,?,?,?)}");
            cs1.setString(1, tarefa.getRef());
            cs1.setString(2, tarefa.getDesignacao());
            cs1.setString(3, tarefa.getDescrInformal());
            cs1.setString(4, tarefa.getDescrTecnica());
            cs1.setInt(5, tarefa.getDuracaoEst());
            cs1.setDouble(6, tarefa.getCustoEst());
            cs1.setString(7, tarefa.getColab().getEmail());
            cs1.setInt(8, nifOrganizacao);
            cs1.setInt(9, Integer.parseInt(tarefa.getCatTarefa().getId().split("-")[1]));

            cs1.executeQuery();
            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException("Error at saveTarefa" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (cs1 != null) {
                cs1.close();
            }
        }
    }

    /**
     * Devolve uma Tarefa existente na base de dados, que tenha a referência
     * passada por parâmetro
     *
     * @param referencia referência da tarefa
     * @return tarefa
     * @throws Exception "Error at getTarefasByOrg"
     */
    @Override
    public Tarefa find(String referencia) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getTarefaByRef(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, referencia);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();

            return montarTarefa(resultSet);

        } catch (SQLException e) {
            throw new SQLException("Error at getTarefasByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private Tarefa montarTarefa(ResultSet rs) throws Exception {
        Tarefa tarefa = null;

        try {
            tarefa = new Tarefa(rs.getString("REFERENCIA"), rs.getString("DESIGNACAO"),
                    rs.getString("DESCINFORMAL"), rs.getString("DESCTECNICA"),
                    rs.getInt("DURACAOEST"), rs.getDouble("CUSTOEST"),
                    fabricaRepositorios.getRepositorioCategoriaTarefa().find(rs.getString("IDCATTAR")),
                    fabricaRepositorios.getRepositorioColaborador().find(rs.getString("EMAILCOLABORADOR")));

            return tarefa;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    /**
     * Devolve uma lista de tarefas a partir da tabela de Tarefa da base de
     * dados.
     *
     * @return lista de tarefas
     * @throws Exception "Error at getAllTarefas"
     */
    @Override
    public List<Tarefa> getAll() throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{ ? = call GETTAREFAS()}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            return montarListaTarefas(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getAllTarefas" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private List<Tarefa> montarListaTarefas(ResultSet rs) throws Exception {
        List<Tarefa> listaTarefas = new ArrayList<>();
        Tarefa tarefa = null;

        try {
            while (rs.next()) {
                tarefa = new Tarefa(rs.getString("REFERENCIA"), rs.getString("DESIGNACAO"),
                        rs.getString("DESCINFORMAL"), rs.getString("DESCTECNICA"),
                        rs.getInt("DURACAOEST"), rs.getDouble("CUSTOEST"),
                        fabricaRepositorios.getRepositorioCategoriaTarefa().find(rs.getString("IDCATTAR")),
                        fabricaRepositorios.getRepositorioColaborador().find(rs.getString("EMAILCOLABORADOR")));
                listaTarefas.add(tarefa);
            }

            return listaTarefas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }

    /**
     * Devolve uma lista de tarefas, a partir da tabela Tarefa, filtrada por
     * organização
     *
     * @param org organização
     * @return lista de tarefas
     * @throws Exception "Error at getListaTarefasByOrg"
     */
    @Override
    public List<Tarefa> getListaTarefasByOrg(Organizacao org) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{ ? = call GETTAREFASORGANIZACAO(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, Integer.valueOf(org.getNif()));
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            return montarListaTarefas(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getListaTarefasByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }

        }
    }
}
