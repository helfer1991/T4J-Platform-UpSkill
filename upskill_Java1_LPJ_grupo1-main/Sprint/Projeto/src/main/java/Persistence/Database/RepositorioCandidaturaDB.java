/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioCandidatura;
import com.company.data.DataHandler;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.Freelancer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioCandidatura
 * @author joaor
 */
public class RepositorioCandidaturaDB extends DataHandler implements RepositorioCandidatura {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de candidaturas na respectiva base de dados.
     * @param cand candidatura
     */
    @Override
    public void save(Candidatura cand) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{call addCandidatura(?,?,?,?,?,?,?)}");

            conn.setAutoCommit(false);

            cs.setDate(1, cand.getDtCandidatura());
            cs.setDouble(2, cand.getValorPretendido());
            cs.setInt(3, cand.getNrDias());
            cs.setString(4, cand.getTxtApr());
            cs.setString(5, cand.getTxtMotiv());
            cs.setInt(6, Integer.valueOf(cand.getFree().getNif()));
            cs.setString(7, cand.getAnuncio().getTarefa().getRef());

            cs.executeUpdate();

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
     * Devolve uma lista de candidaturas a partir da tabela Candidatura da base de dados.
     * @return lista de candidaturas
     * @throws Exception "Error at getAllCandidaturas"
     */
    @Override
    public List<Candidatura> getAll() throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAllCandidaturas}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            
            return montarListaCandidaturas(rs);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllCandidaturas" + e.getMessage());
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
     * Devolve uma candidatura existente na base de dados, que tenha o id passado por parâmetro.
     * @param id identificação da candidatura
     * @return candidatura
     * @throws Exception "Error at getCandidatura"
     */
    @Override
    public Candidatura find(int id) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getCandidaturaById(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);     
            cs.setInt(2, id);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            rs.next();

            return montarCandidatura(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at getCandidatura" + e.getMessage());
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
     * Devolve uma lista de candidaturas, a partir da tabela Candidatura, filtrada por email.
     * @param email email do freelancer
     * @return lista de candidaturas
     * @throws Exception "Error at getAllCandidaturasByFreelancerEmail"
     */
    @Override
    public List<Candidatura> getAllCandidaturasByFreelancerEmail(String email) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAllCandidaturasByFreelancerEmail(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);   
            cs.setString(2, email);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            return montarListaCandidaturas(resultSet);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllCandidaturasByFreelancerEmail" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }
    
    /**
     * Devolve uma lista de candidaturas disponíveis, a partir da tabela Candidatura, filtrada por email.
     * @param email email do freelancer
     * @return lista de candidaturas
     * @throws Exception "Error at getAllCandidaturasDisponiveisByFreelancerEmail"
     */
    @Override
    public List<Candidatura> getCandidaturasDispByFreelancerEmail(String email) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAllCandidaturasDispByFreelancerEmail(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);   
            cs.setString(2, email);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            return montarListaCandidaturas(resultSet);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllCandidaturasDisponiveisByFreelancerEmail" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Altera os parâmetros de uma candidatura.
     * @param cand candidatura
     */
    @Override
    public void update(Candidatura cand) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{call changeCandidatura(?,?,?,?,?)}");

            conn.setAutoCommit(false);

            System.out.println(cand.getId());
            cs.setInt(1, cand.getId());
            cs.setDouble(2, cand.getValorPretendido());
            cs.setInt(3, cand.getNrDias());
            cs.setString(4, cand.getTxtApr());
            cs.setString(5, cand.getTxtMotiv());

            cs.executeUpdate();

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
     * Devolve uma lista de candidaturas, a partir da tabela Candidatura, filtrada pela referência do anúncio 
     * @param anuncio anúncio
     * @return lista de candidaturas
     * @throws Exception "Error at getByAnuncio"
     */
    @Override
    public List<Candidatura> getByAnuncio(Anuncio anuncio) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAllCandidaturasByAnuncio(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);                
            cs.setString(2, anuncio.getTarefa().getRef());
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            
            return montarListaCandidaturas(rs);

        } catch (SQLException e) {
            throw new SQLException("Error at getByAnuncio" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    private Candidatura montarCandidatura(ResultSet rs) throws SQLException, Exception {
        Candidatura candidatura = null;

        try {
            Anuncio anuncio = fabricaRepositorios.getRepositorioAnuncio().find(rs.getString("IDANUNCIO"));
            Freelancer freelancer = fabricaRepositorios.getRepositorioFreelancer().find(rs.getString("email"));
            candidatura = new Candidatura(anuncio, rs.getDate("DATACANDIDATURA"), rs.getDouble("VALORPRETENDIDO"), rs.getInt("NRDIAS"),
            rs.getString("TXTAPRESENTACAO"), rs.getString("TXTMOTIVACAO"), freelancer);
            int id = rs.getInt("ID");
            candidatura.setId(id);

            return candidatura;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    private List<Candidatura> montarListaCandidaturas(ResultSet rs) throws SQLException, Exception {
        ArrayList<Candidatura> tmp = new ArrayList<>();
        Candidatura candidatura = null;
        try {
            while (rs.next()) {
                candidatura = montarCandidatura(rs);
                tmp.add(candidatura);
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
     * Remove uma candidatura e respectivos parâmetros da base de dados
     * @param cand candidatura
     */
    @Override
    public void remove(Candidatura cand) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{call removeCandidatura(?)}");

            conn.setAutoCommit(false);

            cs.setInt(1, cand.getId());

            cs.executeUpdate();

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
}
