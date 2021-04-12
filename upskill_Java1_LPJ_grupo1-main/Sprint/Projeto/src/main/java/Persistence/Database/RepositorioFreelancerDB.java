/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioFreelancer;
import com.company.data.DataHandler;
import com.company.model.CompetenciaTecnica;
import com.company.model.EnderecoPostal;
import com.company.model.Freelancer;
import com.company.model.GrauProficiencia;
import com.company.model.HabilitacaoAcademica;
import com.company.model.ReconhecimentoCT;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioFreelancer
 * @author joaor
 */
public class RepositorioFreelancerDB extends DataHandler implements RepositorioFreelancer {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Devolve uma lista de freelancers a partir da tabela Freelancer da base de dados.
     * @return lista de freelancers
     * @throws SQLException "Error at getAllFreelancers"
     */
    @Override
    public List<Freelancer> getAll() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getFreelancers}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            return montarListaFreelancers(rs, conn);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllFreelancers" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }

    /**
     * Método que grava os atributos de um freelancer na respectiva base de dados.
     * @param free freelancer
     */
    @Override
    public void save(Freelancer free) throws SQLException {
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

            cs.setString(1, free.getEndPostal().getRua());
            cs.setString(2, free.getEndPostal().getCodPostal());
            cs.setString(3, free.getEndPostal().getLocalidade());
            cs.executeQuery();
            
            conn.commit();

            cs = conn.prepareCall("{call addFreelancer(?,?,?)}");
            cs.setString(1, free.getNif());
            cs.setString(2, free.getEmail());
            cs.setInt(3, (id + 1));
            cs.executeQuery();

            cs = conn.prepareCall("{call addReconhecimentoCT(?,?,?,?)}");
            for (ReconhecimentoCT rCT : free.getlRecFree()) {
                cs.setDate(1, rCT.getDataReconhecimento());
                cs.setInt(2, Integer.valueOf(free.getNif().trim()));
                cs.setString(3, rCT.getGrauReconhecido().getValor().trim());
                cs.setString(4, rCT.getGrauReconhecido().getCompT().getId());
                cs.executeQuery();
            }

            cs = conn.prepareCall("{call addHabilitacaoAcademica(?,?,?,?,?)}");
            for (HabilitacaoAcademica hAcad : free.getlHabAc()) {
                cs.setString(1, hAcad.getGrau());
                cs.setString(2, hAcad.getNomeCurso());
                cs.setString(3, hAcad.getFaculdade());
                cs.setDouble(4, hAcad.getMedia());
                cs.setInt(5, Integer.valueOf(free.getNif()));
                cs.executeQuery();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            conn.setAutoCommit(true);
        }
    }

    /**
     * Devolve um Freelancer existente na base de dados, que tenha o email passado por parâmetro.
     * @param email email do freelancer
     * @return Freelancer
     * @throws Exception "Error at findFreelancer"
     */
    @Override
    public Freelancer find(String email) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getFreelancerByEmail(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, email);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            rs.next();

            return montarFreelancer(rs, conn);

        } catch (SQLException e) {
            throw new SQLException("Error at findFreelancer" + e.getMessage());
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
     * Devolve um Freelancer existente na base de dados, que tenha o nif passado por parâmetro.
     * @param nif número de identificação fiscal do freelancer
     * @return freelancer
     * @throws Exception "Error at getAllFreelancers"
     */
    @Override
    public Freelancer findByNif(int nif) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getFreelancerByNIF(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, nif);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            resultSet.next();

            return montarFreelancer(resultSet, conn);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllFreelancers" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if(rs != null){
                rs.close();
            }
            
        }
    }

    private List<Freelancer> montarListaFreelancers(ResultSet rs, Connection conn) throws SQLException {
        List<Freelancer> tmp = new ArrayList<>();
        CallableStatement cs = null;
        try {
            while (rs.next()) {
                tmp.add(montarFreelancer(rs, conn));
            }

        } catch (SQLException e) {
            throw new SQLException("Error at montarListaFreelancers" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            return tmp;
        }
    }

    private Freelancer montarFreelancer(ResultSet rs, Connection conn) throws SQLException, Exception {
        Freelancer free = null;
        ArrayList<HabilitacaoAcademica> tmp1 = new ArrayList<>();
        ArrayList<ReconhecimentoCT> tmp2 = new ArrayList<>();
        CallableStatement cs = null;

        try {
            int nif = rs.getInt("Nif");
            String morada = rs.getString(10);
            String codPostal = rs.getString(11);
            String localidade = rs.getString(12);
            String nome = rs.getString("Nome");
            String email = rs.getString(2);
            String telefone = String.valueOf(rs.getInt("telefone"));

            cs = conn.prepareCall("{ ? = call getHabilitacoesAcademicas(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, nif);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            tmp1.addAll(montarListaHabilitacaoAcademica(resultSet));

            cs = conn.prepareCall("{ ? = call getReconhecimentosCT(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, nif);
            cs.execute();
            resultSet = (ResultSet) cs.getObject(1);

            tmp2.addAll(montarListaReconhecimentoCT(resultSet));

            EnderecoPostal ep = new EnderecoPostal(morada, codPostal, localidade);
            free = new Freelancer(nome, String.valueOf(nif), email, telefone, ep, tmp1, tmp2);
        } catch (SQLException e) {
            throw new SQLException("Error in montarFreelancer" + e.getMessage());

        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return free;
    }

    private List<HabilitacaoAcademica> montarListaHabilitacaoAcademica(ResultSet rs) throws SQLException {
        ArrayList<HabilitacaoAcademica> tmp = new ArrayList<>();
        try {
            while (rs.next()) {
                tmp.add(new HabilitacaoAcademica(rs.getString("grau"), rs.getString("designacaocurso"), rs.getString("nomeinstituicao"), rs.getDouble("mediacurso")));
            }
        } catch (SQLException e) {
            throw new SQLException("Error at montarListaHabilitacaoAcademica" + e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
        return tmp;
    }

    private List<ReconhecimentoCT> montarListaReconhecimentoCT(ResultSet rs) throws Exception {
        ArrayList<ReconhecimentoCT> tmp = new ArrayList<>();
        GrauProficiencia gp = null;
        try {
            while (rs.next()) {
                CompetenciaTecnica ct = fabricaRepositorios.getRepositorioCompetenciaTecnica().find(rs.getString("codcompetenciatecnica"));
                for (GrauProficiencia grauProficiencia : ct.getListaGrausProficiencia()) {
                    if (rs.getString("valorcompetenciatecnicareconhecido").equalsIgnoreCase(grauProficiencia.getValor())) {
                        gp = grauProficiencia;
                    }
                }
                tmp.add(new ReconhecimentoCT(rs.getDate("datareconhecimento"), gp));
            }
        } catch (SQLException e) {
            throw new SQLException("Error at montarListaHabilitacaoAcademica" + e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
        return tmp;
    }

}
