/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioProcessoSeriacao;
import com.company.data.DataHandler;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.Classificacao;
import com.company.model.Colaborador;
import com.company.model.ProcessoSeriacao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa o RepositorioProcessoSeriacao
 * @author joaor
 */
public class RepositorioProcessoSeriacaoDB extends DataHandler implements RepositorioProcessoSeriacao {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de um processo de seriação na respectiva base de dados.
     * @param pSer processo de seriação
     */
    @Override
    public void save(ProcessoSeriacao pSer) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            cs = conn.prepareCall("{call addProcessoSeriacao(?,?,?)}");

            cs.setDate(1, pSer.getDataRealizacao());
            cs.setString(2, pSer.getColab().getEmail());
            cs.setString(3, pSer.getAnuncio().getTarefa().getRef());

            cs.executeUpdate();

            String sql = "{call addClassificacao(?,?,?,?,?)}";

            for (Classificacao classif : pSer.getClassificacoes()) {
                cs = conn.prepareCall(sql);

                cs.setString(1, classif.getCandidatura().getAnuncio().getTarefa().getRef());
                cs.setDate(2, classif.getDataHora());
                cs.setInt(3, classif.getLugarClassificacao());
                cs.setInt(4, classif.getCandidatura().getId());
                cs.setInt(5, Integer.valueOf(classif.getCandidatura().getFree().getNif()));

                cs.executeUpdate();
            }

            sql = "{call addListaColaboradorProcessoSeriacao(?,?)}";

            for (Colaborador colab : pSer.getParticipantes()) {
                cs = conn.prepareCall(sql);
                cs.setString(1, pSer.getAnuncio().getTarefa().getRef());
                cs.setString(2, colab.getEmail());
                cs.executeUpdate();
            }

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
     * Devolve uma lista de classificações, a partir da tabela de Classificacao, filtrada pelo id do processo de seriação.
     * @param id identificação processo de seriação
     * @return lista classificações
     * @throws Exception "Error at getListClassificacoesByProcSerId"
     */
    @Override
    public List<Classificacao> getListClassificacoesByProcSerId(String id) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getClassificacoesByProcessoSeriacao(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR); 
            cs.setString(2, id);
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);

            Classificacao classif = null;
            ArrayList<Classificacao> tmp = montarListaClassificacoes(resultSet);

            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getListClassificacoesByProcSerId" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    /**
     * Devoleve uma lista de processos de seriação, a partir da tabela ProcessoSeriacao, filtrada pelo nif de organização
     * @param nif número de identificação fiscal da organização
     * @return lista de processos de seriação
     * @throws Exception "Error at getProcessosSeriacaoByOrgNif"
     */
    @Override
    public List<ProcessoSeriacao> getProcessosSeriacaoByOrgNif(String nif) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getProcessosSeriacaoByOrgNif(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);  
            cs.setInt(2, Integer.valueOf(nif));
            cs.execute();
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            
            ArrayList<ProcessoSeriacao> tmp = montarListaProcessoSeriacao(resultSet, conn);

            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at getProcessosSeriacaoByOrgNif" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    private ArrayList<Classificacao> montarListaClassificacoes(ResultSet rs) throws Exception {
        Classificacao classif = null;
        ArrayList<Classificacao> tmp = new ArrayList<>();
        try {

            while (rs.next()) {
                Candidatura cand = fabricaRepositorios.getRepositorioCandidatura().find(rs.getInt("idCandidatura"));
                classif = new Classificacao(rs.getInt("lugarClassificacao"), rs.getDate("datahora"), cand);
                tmp.add(classif);
            }

            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at montarListaClassificacoes" + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private ArrayList<ProcessoSeriacao> montarListaProcessoSeriacao(ResultSet rs, Connection conn) throws Exception {
        CallableStatement cs = null;
        ArrayList<ProcessoSeriacao> tmp = new ArrayList<>();
        ArrayList<Classificacao> classificacoes = new ArrayList<>();
        ArrayList<Colaborador> participantes = new ArrayList<>();
        ProcessoSeriacao pS = null;
        try {
            while (rs.next()) {
                classificacoes = new ArrayList<>();
                String anuncioId = rs.getString("idAnuncio");
                Anuncio anuncio = fabricaRepositorios.getRepositorioAnuncio().find(anuncioId);
                Colaborador colab = fabricaRepositorios.getRepositorioColaborador().find(rs.getString("emailColaborador"));
                Date data = rs.getDate("DATAREALIZACAO");

                cs = conn.prepareCall("{ ? = call getParticipantesProcessoSeriacao(?)}");
                cs.registerOutParameter(1, OracleTypes.CURSOR);  
                cs.setString(2, anuncioId);
                cs.execute();
                ResultSet resultSet = (ResultSet) cs.getObject(1);
                
                participantes = montarListaParticipanes(resultSet);

                cs = conn.prepareCall("{ ? = call getClassificacoesByProcessoSeriacao(?)}");
                cs.registerOutParameter(1, OracleTypes.CURSOR);  
                cs.setString(2, anuncioId);
                cs.execute();
                resultSet = (ResultSet) cs.getObject(1);
                
                classificacoes = montarListaClassificacoes(resultSet);

                pS = new ProcessoSeriacao(data, colab, anuncio, participantes, classificacoes);
                tmp.add(pS);

            }

            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Error at montarListaClassificacoes" + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private ArrayList<Colaborador> montarListaParticipanes(ResultSet rs) throws Exception {
        ArrayList<Colaborador> participantes = new ArrayList<>();
        Colaborador partic = null;
        try {
            while (rs.next()) {
                partic = fabricaRepositorios.getRepositorioColaborador().find(rs.getString("emailColaborador"));
                participantes.add(partic);
            }

            return participantes;
        } catch (SQLException e) {
            throw new SQLException("Error at montarListaParticipanes" + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
