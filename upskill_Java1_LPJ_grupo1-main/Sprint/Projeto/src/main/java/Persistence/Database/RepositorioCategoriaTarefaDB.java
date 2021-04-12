/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioCategoriaTarefa;
import com.company.data.DataHandler;
import com.company.model.AreaAtividade;
import com.company.model.CaraterCT;
import com.company.model.CategoriaTarefa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioCategoriaTarefa
 * 
 * @author joaor
 */
public class RepositorioCategoriaTarefaDB extends DataHandler implements RepositorioCategoriaTarefa {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de uma categoria de tarefa na respectiva base de dados.
     * 
     * @param ct categoria de tarefa
     */
    @Override
    public void Save(CategoriaTarefa ct) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            conn.setAutoCommit(false);

            //insere uma categoria na tabela
            cs = conn.prepareCall("{ call addCategoriaTarefa(?, ?)}");

            cs.setString(1, ct.getDescricao());
            cs.setString(2, ct.getAreaAtividade().getId());
            cs.executeQuery();

            //vai buscar o id da ultima categoria criada
            rs = stmt.executeQuery("SELECT MAX(ID) ID FROM CATEGORIATAREFA");
            int maxId = 1;
            if (rs.next()) {
                maxId = rs.getInt("ID");
            }

            cs = conn.prepareCall("{call addCaraterCT(?, ?, ?, ?)}");
            //preenche a tabela CaracterCT com competencias técnicas da categoria de tarefa criada
            for (CaraterCT caracter : ct.getLcompTec()) {
                cs.setInt(1, maxId);
                cs.setString(2, caracter.getCodCompetenciaTecnica());
                cs.setInt(3, caracter.getObrigatorio());
                System.out.println( caracter.getGrauMinimoProficiencia().split(" ")[0]);
                cs.setString(4, caracter.getGrauMinimoProficiencia().split(" ")[0]);
                cs.executeQuery();
            }

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null){
                rs.close();
            }
            conn.setAutoCommit(true);
        }
    }

    /**
     * Devolve uma lista de categorias de tarefa a partir da tabela de CategoriaTarefa da base de dados.
     * 
     * @return lista de categorias de tarefa
     * @throws Exception "Error at getAllCategoriaTarefa"
     */
    @Override
    public List<CategoriaTarefa> getAll() throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call GETCATEGORIATAREFA()}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            
            return montarListaCategoriaTarefa(rs, conn);
        } catch (SQLException e) {
            throw new SQLException("Error at getAllCategoriaTarefa" + e.getMessage());
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
     * Devolve uma lista de competências técnicas, a partir da tabela de CaraterCT, filtrada pelo id da Categoria de tarefa.
     * 
     * @param id identificação da categoria de tarefa
     * @return lista de competências técnicas, filtrada pelo id da Categoria de tarefa
     * @throws Exception "Error at getAllCategoriaTarefa"
     */
    @Override
    public List<CaraterCT> getCaraterCTByCatTarID(String id) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call GETCARATERCTBYCATEGORIATAREFAID(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, Integer.valueOf(id));
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            
            return montarListaCaraterCT(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at getCaraterCTByCatTarId" + e.getMessage());
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
     * Devolve uma lista de categorias de tarefa a partir do resultado de uma query de busca da respectiva conecção.
     * 
     * @param rs resultado do pedido de todas as categorias de tarefa (GETCATEGORIATAREFA)
     * @param conn conecção à base de dados
     * @return lista de categorias de tarefa 
     * @throws Exception "Error at getAllCategoriaTarefa"
     */
    private List<CategoriaTarefa> montarListaCategoriaTarefa(ResultSet rs, Connection conn) throws Exception {
        ArrayList<CategoriaTarefa> listaCT = new ArrayList<>();
        CategoriaTarefa categoria;

        CallableStatement cs = null;

        try {
            while (rs.next()) {

                categoria = montarCategoriaTarefa(rs, conn);

                listaCT.add(categoria);
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return listaCT;
    }

    /**
     * Devolve uma lista de Caracter de competências técnicas a partir do resultado de um pedido à base de dados
     * 
     * @param rs resultado do pedido de todas os Caracter de competências técnicas GETCARATERCTBYCATEGORIATAREFAID
     * @return lista de Caracter de competências técnicas
     */
    private List<CaraterCT> montarListaCaraterCT(ResultSet rs) throws SQLException {
        ArrayList<CaraterCT> lcompt = new ArrayList<>();
        CaraterCT cCT = null;
        try {
            while (rs.next()) {
                cCT = new CaraterCT(rs.getString("idcategoriatarefa"), rs.getString("codcompetenciatecnica"), rs.getInt("obrigatorio"), rs.getString("grauminimoproficiencia"));
                lcompt.add(cCT);
            }

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return lcompt;
    }

    /**
     * Devolve uma categoria de tarefa a partir do resultado de um pedido à base de dados
     * @param rs resultado do pedido de uma categoria de tarefa
     * @param conn conecção à base de dados
     * @return categoria de tarefa 
     */
    private CategoriaTarefa montarCategoriaTarefa(ResultSet rs, Connection conn) throws Exception {
        CallableStatement cs = null;
        CategoriaTarefa categoria;

        try {
            String descricao = rs.getString("DESCRICAO");
            AreaAtividade areaAtividade = fabricaRepositorios.getRepositorioAreaAtividade().find(rs.getString("IDAREAATIVIDADE"));
            List<CaraterCT> lcompt = new ArrayList<>();

            cs = conn.prepareCall("{ ? = call GETCARATERCTBYCATEGORIATAREFAID(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);  
            cs.setInt(2, rs.getInt("id"));
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            
            lcompt = montarListaCaraterCT(resultSet);

            categoria = new CategoriaTarefa(descricao, areaAtividade, lcompt);
            categoria.setIdCategoria(String.valueOf(rs.getInt("id")));
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return categoria;
    }

    /**
     * Devolve uma Categoria de Tarefa existente na base de dados, que tenha o id passado por parâmetro 
     * @param id identificação da categoria de tarefa
     * @return categoria de tarefa 
     * @throws Exception "Error at findCategoriaTarefa"
     */
    @Override
    public CategoriaTarefa find(String id) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
//      Linha adicionadas pos apresentacao com nuno morgado e autorizadas
        ResultSet rs = null;
//        
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call GETCATEGORIATAREFABYID(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);   
            cs.setString(2, id);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();
            
            return montarCategoriaTarefa(resultSet, conn);

        } catch (SQLException e) {
            throw new SQLException("Error at findCategoriaTarefa" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
//          Linhas adicionadas pos apresentacao com nuno morgado e autorizadas
            if (rs != null) {
                rs.close();
            }
//            
        }
    }

}
