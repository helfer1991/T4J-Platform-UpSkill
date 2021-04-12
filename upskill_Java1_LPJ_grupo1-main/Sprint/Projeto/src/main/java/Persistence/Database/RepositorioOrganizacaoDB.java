/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioOrganizacao;
import com.company.data.DataHandler;
import com.company.model.Colaborador;
import com.company.model.EnderecoPostal;
import com.company.model.Organizacao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioOrganizacao
 * @author Asus
 */
public class RepositorioOrganizacaoDB extends DataHandler implements RepositorioOrganizacao {
    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de uma organização na respectiva base de dados.
     * @param org organização
     */
    @Override
    public void save(Organizacao org) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            conn.setAutoCommit(false);

            //id endereco postal
            rs = stmt.executeQuery("select max(id) id from enderecopostal");
            int id = 1;
            if (rs.next()) {
                id = rs.getInt("id");
            }


            cs = conn.prepareCall("{call addEnderecoPostal(?,?,?)}");

            cs.setString(1, org.getEndPostal().getRua());
            cs.setString(2, org.getEndPostal().getCodPostal());
            cs.setString(3, org.getEndPostal().getLocalidade());
            cs.executeQuery();
            
            conn.commit();
            
            cs = conn.prepareCall("{call addOrganizacao(?,?,?,?,?)}");

            cs.setInt(1, Integer.valueOf(org.getNif()));
            cs.setString(2, org.getEmail());
            cs.setInt(3, id + 1);
            cs.setString(4, org.getGestor().getEmail());
            cs.setString(5, org.getSite());
            cs.executeQuery();
            

            cs = conn.prepareCall("{call addColaborador(?,?)}"); // inserir gestor como colaborador
            cs.setString(1, org.getGestor().getEmail());
            cs.setInt(2, Integer.valueOf(org.getNif()));
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
     * Devolve uma organização existente na base de dados, que tenha o nif passado por parâmetro 
     * @param nif número de identificação fiscal da organização
     * @return organização
     * @throws Exception "Error at find"
     */
    @Override
    public Organizacao find(String nif) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getOrgByNif(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR); 
            cs.setInt(2, Integer.parseInt(nif));
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();
            
            return montarOrganizacao(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at find" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devolve uma lista de organizações a partir da tabela Organizacao da base de dados.
     * @return lista de organizações
     * @throws Exception "Error at getAll"
     */
    @Override
    public List<Organizacao> getAll() throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAllOrganizacoes()}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);          

            return montarListaOrganizacoes(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at getAll" + e.getMessage());
        } finally {
            if(cs!=null){
                cs.close();
            }
            try {
                //
            } catch (Exception e) {
            }
        }
    }

    /**
     * Devolve uma organização existente na base de dados, que tenha o email passado por parâmetro 
     * @param email email do colaborador
     * @return organização
     * @throws Exception "Error at findByColabEmail"
     */
    @Override
    public Organizacao findByColabEmail(String email) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getOrgByColabEmail(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, email);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();
            
            return montarOrganizacao(resultSet);
        } catch (SQLException e) {
            throw new SQLException("Error at findByColabEmail" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private Organizacao montarOrganizacao(ResultSet rs) throws Exception {
        Organizacao org = null;
        EnderecoPostal end = null;
        Colaborador colab = null;
        
        try {
            end = new EnderecoPostal(rs.getString("MORADA"), rs.getString("CODIGOPOSTAL"), rs.getString("LOCALIDADE"));
            colab = fabricaRepositorios.getRepositorioColaborador().findGestorByOrg(rs.getInt("NIF"));
            String nome = rs.getString("NOME");
            String nif = rs.getString(String.valueOf("NIF"));
            String site = rs.getString("WEBSITE");
            String telefone = rs.getString(String.valueOf("TELEFONE"));
            String email = rs.getString("EMAIL");
            org = new Organizacao(nome, nif, site, telefone, email, end, colab);
            org.setListaColaborador(fabricaRepositorios.getRepositorioColaborador().getColaboradoresByOrg(org));
            
            return org;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
    }

    private List<Organizacao> montarListaOrganizacoes(ResultSet rs/*, List<Colaborador> tmpColab*/) throws Exception {
        List<Organizacao> tmp = new ArrayList<>();
        try {
            Organizacao org = null;
            Colaborador colab = null;
            EnderecoPostal end = null;
            while (rs.next()) {
                end = new EnderecoPostal(rs.getString("MORADA"), rs.getString("CODIGOPOSTAL"), rs.getString("LOCALIDADE"));
                colab = fabricaRepositorios.getRepositorioColaborador().findGestorByOrg(rs.getInt("NIF"));
                String nome = rs.getString("NOME");
                String nif = rs.getString(String.valueOf("NIF"));
                String site = rs.getString("WEBSITE");
                String telefone = rs.getString(String.valueOf("TELEFONE"));
                String email = rs.getString("EMAIL");
                org = new Organizacao(nome, nif, site, telefone, email, end, colab);
                org.setListaColaborador(fabricaRepositorios.getRepositorioColaborador().getColaboradoresByOrg(org));
                tmp.add(org);
            }

            return tmp;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
    } 
}
