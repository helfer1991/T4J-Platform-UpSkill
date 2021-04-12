/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Database;

import Persistence.FabricaRepositorios;
import Persistence.RepositorioAnuncio;
import com.company.data.DataHandler;
import com.company.model.Anuncio;
import com.company.model.Organizacao;
import com.company.model.Tarefa;
import com.company.model.TipoRegimento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Classe com os métodos necessários para aceder à base de dados que implementa
 * o RepositorioAnuncio
 *
 * @author joaor
 */
public class RepositorioAnuncioDB extends DataHandler implements RepositorioAnuncio {

    FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Método que grava os atributos de um anúncio na respectiva base de dados.
     *
     * @param anuncio anúncio
     */
    @Override
    public void save(Anuncio anuncio) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = getConnection();
            cs = conn.prepareCall("{call addAnuncio(?,?,?,?,?,?,?,?,?)}");

            conn.setAutoCommit(false);

            cs.setString(1, anuncio.getTarefa().getRef());
            cs.setDate(2, anuncio.getdInicioPublicitacao());
            cs.setDate(3, anuncio.getdFimPublicitacao());
            cs.setDate(4, anuncio.getdInicioCandidatura());
            cs.setDate(5, anuncio.getdFimCandidatura());
            cs.setDate(6, anuncio.getdInicioSeriacao());
            cs.setDate(7, anuncio.getdFimSeriacao());
            cs.setInt(8, anuncio.getRegimento().getId());
            int nif = Integer.parseInt(fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(anuncio.getColab().getEmail()).getNif());
            cs.setInt(9, nif);

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
     * Devolve um anúncio existente na base de dados, que tenha a referência
     * passada por parâmetro.
     *
     * @param referencia referência do anúncio
     * @return anúncio
     * @throws SQLException "Error at getAnuncioByRef"
     */
    @Override
    public Anuncio find(String referencia) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAnuncioByRef(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, referencia);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            rs.next();

            return montarAnuncio(rs);

        } catch (SQLException e) {
            throw new SQLException("Error at getAnuncioByRef" + e.getMessage());
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
     * Devolve uma lista de anúncios a partir da tabela Anuncio da base de
     * dados.
     *
     * @return lista de anúncios
     * @throws SQLException "Error at getAllAnuncios"
     */
    @Override
    public List<Anuncio> getAll() throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call GETALLANUNCIOS}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            return montarListaAnuncios(rs);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllAnuncios" + e.getMessage());
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
     * Devolve uma lista de anúncios, a partir da tabela Anuncio, filtrada por
     * organização.
     *
     * @param org organização
     * @return lista de anúncios
     * @throws SQLException "Error at getAnunciosByOrg"
     */
    @Override
    public List<Anuncio> getAnunciosByOrg(Organizacao org) throws SQLException, Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call getAnunciosByOrg(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, Integer.parseInt(org.getNif()));
            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            return montarListaAnuncios(rs);
        } catch (SQLException e) {
            throw new SQLException("Error at getAnunciosByOrg" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    private Anuncio montarAnuncio(ResultSet rs) throws SQLException, Exception {
        Anuncio anuncio = null;
        try {
            Tarefa tar = fabricaRepositorios.getRepositorioTarefa().find(rs.getString("id"));
            TipoRegimento tp = fabricaRepositorios.getRepositorioTipoRegimento().findById(rs.getInt("idTipoRegimento"));
            anuncio = new Anuncio(
                    rs.getDate("dtiniciopublicacao"), rs.getDate("dtfimpublicacao"), rs.getDate("dtiniciocandidatura"),
                    rs.getDate("dtfimcandidatura"), rs.getDate("dtinicioseriacao"), rs.getDate("dtfimseriacao"),
                    tar, tar.getColab(), tp
            );

            return anuncio;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private List<Anuncio> montarListaAnuncios(ResultSet rs) throws Exception {
        List<Anuncio> listaAnuncios = new ArrayList<>();
        Anuncio anuncio = null;

        try {
            while (rs.next()) {
                anuncio = montarAnuncio(rs);
                listaAnuncios.add(anuncio);
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return listaAnuncios;
    }

    /**
     * Devolve uma lista de anúncios disponíveis.
     *
     * @return lista de anúncios disponíveis
     * @throws Exception "Error at getAllAnunciosDisponiveis"
     */
    @Override
    public List<Anuncio> getAnunciosDisponiveis() throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            cs = conn.prepareCall("{ ? = call GETALLANUNCIOSDISPONIVEIS}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            return montarListaAnuncios(rs);

        } catch (SQLException e) {
            throw new SQLException("Error at getAllAnunciosDisponiveis" + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
