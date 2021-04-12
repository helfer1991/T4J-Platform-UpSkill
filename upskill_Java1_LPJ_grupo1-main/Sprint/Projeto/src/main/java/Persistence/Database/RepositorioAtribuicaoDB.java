/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioAtribuicao;
import com.company.data.DataHandler;
import com.company.model.Atribuicao;
import com.company.model.Classificacao;
import com.company.model.Organizacao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa
 * o RepositorioAtribuicao
 *
 * @author joaor
 */
public class RepositorioAtribuicaoDB extends DataHandler implements RepositorioAtribuicao {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos da atribuição na respectiva base de dados.
     *
     * @param at atribuição
     * @throws Exception
     */
    @Override
    public void save(Atribuicao at) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{call addAtribuicao(?, ?, ?, ?, ?, ?, ?)}");

            conn.setAutoCommit(false);

            cs.setString(1, at.getId());
            cs.setInt(2, Integer.valueOf(fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(at.getPickedClass().getCandidatura().getAnuncio().getColab().getEmail()).getNif()));
            cs.setInt(3, Integer.valueOf(at.getPickedClass().getCandidatura().getFree().getNif()));
            cs.setDate(4, addDays(at.getPickedClass().getCandidatura().getAnuncio().getdFimSeriacao(), 1));
            cs.setDate(5, addDays(at.getPickedClass().getCandidatura().getAnuncio().getdFimSeriacao(), at.getPickedClass().getCandidatura().getNrDias() + 1));
            cs.setDouble(6, at.getPickedClass().getCandidatura().getValorPretendido());
            cs.setString(7, at.getPickedClass().getCandidatura().getAnuncio().getTarefa().getRef());

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
     * Devolve uma atribuição existente na base de dados, que tenha o id passado
     * por parâmetro.
     *
     * @param id identificação da atribuição
     * @return atribuição
     * @throws Exception "Error at find Atribuicao"
     */
    @Override
    public Atribuicao find(String id) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAtribuicaoByID(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, id);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            rs.next();

            return montarAtribuicao(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at find Atribuicao" + e.getMessage());
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
     * Devolve uma lista de atribuições a partir da tabela Atribuicao da base de
     * dados, filtrada por organização.
     *
     * @param org organização
     * @return lista de atribuições
     * @throws Exception "Error at getAllByOrg"
     */
    @Override
    public List<Atribuicao> getAllByOrg(Organizacao org) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAtribuicaoByOrg(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, Integer.valueOf(org.getNif()));
            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            return montarListaAtribuicao(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at getAllByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    private List<Atribuicao> montarListaAtribuicao(ResultSet rs) throws Exception {
        List<Atribuicao> listaAtribuicao = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAtribuicao.add(montarAtribuicao(rs));
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return listaAtribuicao;
    }

    private Atribuicao montarAtribuicao(ResultSet rs) throws Exception {
        Atribuicao atribuicao = null;
        Classificacao pickedClass = null;
        try {
            for (Classificacao classif : fabricaRepositorios.getRepositorioProcessoSeriacao().getListClassificacoesByProcSerId(rs.getString("idAnuncio"))) {
                if (String.valueOf(rs.getInt("nifFreelancer")).equalsIgnoreCase(classif.getCandidatura().getFree().getNif())) {
                    pickedClass = classif;
                }
            }

            atribuicao = new Atribuicao(rs.getString("id"), pickedClass, addDays(rs.getDate("dataInicioRealizacao"), -1));

            return atribuicao;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
