/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioColaborador;
import com.company.data.DataHandler;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioColaborador
 * @author joaor
 */
public class RepositorioColaboradorDB extends DataHandler implements RepositorioColaborador {
    
    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Devolve uma lista de colaboradores filtrada pelo nif de Organizacao da base de dados.
     * @param org organização
     * @return lista de colaboradores
     * @throws SQLException "Error at getColaboradorByOrg"
     */
    @Override
    public List<Colaborador> getColaboradoresByOrg(Organizacao org) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call GetColaboradoresByOrg(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, Integer.parseInt(org.getNif()));
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            
            return montarListaColabs(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getColaboradorByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve uma lista de colaboradores a partir da tabela Colaborador da base de dados.
     * @return lisa de colaboradores
     * @throws SQLException "Error at getAllColaboradores"
     */
    @Override
    public List<Colaborador> getAllColaboradores() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getColaboradores()}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            
            return montarListaColabs(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getAllColaboradores" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Método que grava os atributos de um colaborador na respectiva base de dados.
     * @param colab colaborador
     */
    @Override
    public void save(Colaborador colab) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);


            cs = conn.prepareCall("{call addColaborador(?,?)}");
            cs.setString(1, colab.getEmail());
            
            String nifOrg = fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(Plataforma.getInstance().getUsersAPI().getEmail()).getNif();
            cs.setInt(2, Integer.valueOf(nifOrg));
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
     * Devolve um colaborador existente na base de dados, que tenha o email passado por parâmetro 
     * @param email email
     * @return colaborador
     * @throws Exception "Error at getColaboradorByOrg"
     */
    @Override
    public Colaborador find(String email) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getColaborador(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, email);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();
            
            return montarColaborador(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getColaboradorByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }
    
    /**
     * Devolve um gestor existente na base de dados, que tenha o nif passado por parâmetro, a partir da tabela Organizacao e Utilizador
     * @param nif número de identificação fiscal da organização
     * @return gestor da organização
     * @throws Exception "Error at getColaboradorByOrg"
     */
    @Override
    public Colaborador findGestorByOrg(int nif) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getGestorOrg(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, nif);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();
            
            return montarColaborador(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getColaboradorByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private List<Colaborador> montarListaColabs(ResultSet rs) throws SQLException {
        ArrayList<Colaborador> colabList = new ArrayList<>();
        Colaborador colab = null;
        try {
            while (rs.next()) {
                colab = new Colaborador(rs.getString("Nome"), String.valueOf(rs.getInt("telefone")), rs.getString("email"));
                colabList.add(colab);
            }

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return colabList;
    }

    private Colaborador montarColaborador(ResultSet rs) throws SQLException {
        Colaborador colab = null;
        try {
            colab = new Colaborador(rs.getString("Nome"), String.valueOf(rs.getInt("telefone")), rs.getString("email"));

            return colab;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }finally{
            if(rs!=null){
                rs.close();
            }
        }
    }
}
