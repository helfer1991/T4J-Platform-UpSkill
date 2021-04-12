/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.*;
import com.company.utils.Constantes;
import java.sql.SQLException;

import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para
 * registar novos Colaboradores.
 *
 * @author Asus
 */
public class RegistarColaboradorController {

    private Plataforma plat;
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Constrói uma instância de EspecificarColaboradorController. Inicializa a
     * plataforma com uma cópia da mesma.
     */
    public RegistarColaboradorController() throws SQLException {
        this.plat = Plataforma.getInstance();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Colaborador construído com os
     * parâmetros de entrada.
     *
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     */
    public void registaColaborador(String nome, String telefone, String email) throws Exception {
        try {
            Organizacao org = fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(plat.getUsersAPI().getEmail());
            Colaborador c = new Colaborador(nome, telefone, email);
            registaColaboradorAPI(Constantes.ROLE_COLABORADOR_ORGANIZACAO, c);
            fabricaRepositorios.getRepositorioColaborador().save(c);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve a lista de colaboradores da organização.
     *
     * @return lista de colaboradores da organização
     */
    public List<Colaborador> getListColaboradores() throws SQLException, Exception {
        Organizacao org = fabricaRepositorios.getRepositorioOrganizacao().findByColabEmail(plat.getUsersAPI().getEmail());
        return fabricaRepositorios.getRepositorioColaborador().getColaboradoresByOrg(org);
    }

    private boolean registaColaboradorAPI(String funcao, Colaborador c) {
        return plat.getUsersAPI().registerUserWithRoles(c.getNome(), c.getEmail(),c.getTelefone(), plat.getAlgoritmoGeradorPwd().geraPassword(), funcao);
    }
}
