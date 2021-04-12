/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import java.util.List;
import com.company.model.AreaAtividade;
import com.company.model.GrauProficiencia;
import com.company.utils.Constantes;
import com.company.model.Plataforma;
import com.company.model.CompetenciaTecnica;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para
 * registar novas Competências Técnicas.
 *
 * @author Asus
 */
public class RegistarCompetenciaTecnicaController {

    private Plataforma plat;
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    private CompetenciaTecnica ct = null;

    /**
     * Constrói uma instância de EspecificarCompetenciaTecnicaController. Valida
     * o acesso do utilizador 'Administrador' para efectuar o registo das
     * competências técnicas.
     */
    public RegistarCompetenciaTecnicaController() {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_ADMINISTRADOR)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.plat = Plataforma.getInstance();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Competência Técnica construído
     * com os parâmetros de entrada.
     *
     * @param id identificação da competência técnica
     * @param descBreve descrição breve da competência técnica
     * @param descDetalhada descrição detalhada da competência técnica
     * @param areaAtividade área de atividade em que se vai enquadrar a
     * Competência Técnica
     */
    public void RegistaCompetenciaTecnica(String id, String descBreve, String descDetalhada, AreaAtividade areaAtividade) throws Exception {
        try {
            this.ct = new CompetenciaTecnica(id, descBreve, descDetalhada, areaAtividade);
            fabricaRepositorios.getRepositorioCompetenciaTecnica().save(ct);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Valida e adiciona um novo objecto do tipo grau de proficiência construído com os parâmetros de entrada.
     * @param valor valor do grau de proficiência
     * @param designacao designação do grau de proficiência
     * @throws Exception 
     */
    public void RegistaGrauProficiencia(String valor, String designacao) throws Exception {
        try {
            GrauProficiencia gp = new GrauProficiencia(valor, designacao, ct);
            ct.addGrauProficiencia(gp);
            fabricaRepositorios.getRepositorioGrauProficiencia().save(gp);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve uma lista de áreas de atividade existentes.
     *
     * @return lista de áreas de atividade existentes
     */
    public List<AreaAtividade> getAreasAtividade() throws Exception {
        return fabricaRepositorios.getRepositorioAreaAtividade().getAll();
    }

    /**
     * Devolve uma cópia da lista de competências técnicas existentes.
     *
     * @return lista de competências técnicas existentes
     */
    public List<CompetenciaTecnica> getListCompTec() throws Exception {
        return fabricaRepositorios.getRepositorioCompetenciaTecnica().getAll();
    }

    /**
     * Devolve a lista de descrições e respectivos id's, de áreas de atividade.
     *
     * @return lista de descrições e respectivos id's, de áreas de atividade, do
     * tipo ArrayList
     */
    public ArrayList<String> getListaAreaAtividadeIdDescBreve() throws SQLException, Exception {
        String idDescBreve = "";
        ArrayList<String> list = new ArrayList<>();
        for (AreaAtividade aa : getAreasAtividade()) {
            idDescBreve = "ID: " + aa.getId() + " \n" + "DescBreve: " + aa.getDescBreve();
            list.add(idDescBreve);
        }

        return list;
    }

    /**
     * Devolve uma área de atividade existente na lista de áreas de atividade
     * com id igual ao passado por parâmetro.
     *
     * @param string identificação da area de atividade
     * @return área de atividade
     */
    public AreaAtividade getAreaAtividadeById(String string) throws SQLException, Exception {
        String trimmed[] = string.trim().split(" ");

        return fabricaRepositorios.getRepositorioAreaAtividade().find(trimmed[1]);
    }

    /**
     * Devolve uma lista de graus de proficiência, filtrada por uma competência técnica passada por parâmetro.
     * @param selectedItem competência técnica selecionada
     * @return lista de graus de proficiência
     * @throws SQLException
     * @throws Exception 
     */
    public List<GrauProficiencia> getCompTecGrausProf(CompetenciaTecnica selectedItem) throws SQLException, Exception {
        return fabricaRepositorios.getRepositorioGrauProficiencia().getGrausProfByCompTec(selectedItem);
    }

}
