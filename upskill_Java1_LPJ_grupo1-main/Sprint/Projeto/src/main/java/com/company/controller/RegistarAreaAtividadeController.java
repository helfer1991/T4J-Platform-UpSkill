/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.AreaAtividade;
import com.company.utils.Constantes;
import com.company.model.Plataforma;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Áreas de atividade.
 * 
 * @author Asus
 */
public class RegistarAreaAtividadeController {
    private Plataforma plat;
    private AreaAtividade areaAtividade;
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();
    
    /**
     * Constrói uma instância de EspecificarAreaAtividadeController.
     * Valida o acesso do utilizador 'Administrador' para efectuar o registo de áreas de atividade.
     */
    public RegistarAreaAtividadeController() {
        if(!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_ADMINISTRADOR))
            throw new IllegalStateException("Utilizador não Autorizado");
        this.plat = Plataforma.getInstance();
    }
    
    /**
     * Valida e adiciona um novo objecto do tipo Area de Atividade construído com os parâmetros
     * de entrada.
     * 
     * @param id identificação da area de atividade
     * @param descBreve descrição breve da area de atividade
     * @param descDetalhada descrição detalhada da area de atividade
     */
    public void registarAreaAtividade(String id, String descBreve, String descDetalhada) throws Exception {
        try {
            AreaAtividade aa = new AreaAtividade(id, descBreve, descDetalhada);
            fabricaRepositorios.getRepositorioAreaAtividade().save(aa);
        }
        catch(RuntimeException ex) {
            this.areaAtividade = null;
            throw new Exception (ex);
        }
    }
   
    /**
     * Devolve uma cópia da lista de áreas de atividade existentes.
     * 
     * @return cópia da lista de áreas de atividade existentes
     */
    public List<AreaAtividade> getAll() throws SQLException, Exception {
        List<AreaAtividade> listAA = fabricaRepositorios.getRepositorioAreaAtividade().getAll();
        return listAA;
    }

    /**
     * Devolve a descrição textual da área de atividade.
     * 
     * @return descrição textual da área de atividade
     */
    public String getAreaAtividadeString() {
        return this.areaAtividade.toString();
    }
}
