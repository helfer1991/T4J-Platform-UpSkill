/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import Persistence.Database.FabricaRepositoriosDB;
import Persistence.FabricaRepositorios;
import com.company.model.Colaborador;
import com.company.model.Organizacao;
import com.company.model.Plataforma;
import com.company.model.EnderecoPostal;
import com.company.utils.Constantes;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Organizações.
 * 
 * @author Asus
 */
public class RegistarOrganizacaoController {

    private Plataforma plat;
    private Organizacao org;
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDB();

    /**
     * Constrói uma instância de RegistarOrganizacaoController. Inicializa a
     * plataforma com uma cópia da mesma, e copia a lista de organizações
     * existentes.
     */
    public RegistarOrganizacaoController() {
        this.plat = Plataforma.getInstance();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Organização construído com os parâmetros
     * de entrada.
     * 
     * @param nome nome da organização
     * @param nif número de identificação fiscal da organização
     * @param site endereço web da organização
     * @param telefone contacto telefónico da organização
     * @param email e-mail da organização
     * @param rua rua da organização
     * @param codPostal código postal da organização
     * @param localidade localidade da organização
     * @param nomeGestor nome do gestor da organização
     * @param emailGestor e-mail do gestor da organização
     * @param tlfGestor telefone do gestor da organização
     */
    public void registarOrganizacao(String nome, String nif, String site, String telefone,
            String email, String rua, String codPostal, String localidade,
            String nomeGestor, String emailGestor, String tlfGestor) throws Exception {
        try {
            EnderecoPostal morada = Organizacao.novoEnderecoPostal(rua, codPostal, localidade);
            Colaborador c = Organizacao.novoColaborador(nomeGestor, tlfGestor, emailGestor);
            org = new Organizacao(nome, nif, site, telefone, email, morada, c);
            plat.getUsersAPI().registerUserWithRoles(org.getGestor().getNome(), org.getGestor().getEmail(), org.getGestor().getTelefone(), plat.getAlgoritmoGeradorPwd().geraPassword(), Constantes.ROLE_GESTOR_ORGANIZACAO);
            plat.getUsersAPI().registerUser(org.getNome(), org.getEmail(), org.getTelefone(), plat.getAlgoritmoGeradorPwd().geraPassword());
            
            fabricaRepositorios.getRepositorioOrganizacao().save(org);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * Devolve a descrição textual da Organização.
     *
     * @return descrição textual da Organização no formato: Nome - NIF - Site -
     * Telefone - Email - Endereço postal - Gestor.
     */
    public String getOrganizacaoString() {
        return this.org.toString();
    }
}
