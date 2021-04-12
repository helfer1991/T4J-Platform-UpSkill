/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Asus
 */
public class OrganizacaoTest {
    Organizacao o1;
    Organizacao o2;
    EnderecoPostal end;
    Colaborador c2;
    Colaborador c3;
    Colaborador c4;
    
    public OrganizacaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        o1 = new Organizacao("Blip", "500123321", "www.blip.pt", "223456789", "blip@blip.pt", new EnderecoPostal("rua", "1234", "senhora da hora"), new Colaborador("Poupas", "219384756", "cenas@gmail.pt"));
        o2 = o1;
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getGestor method, of class Organizacao.
     */
    @Test
    public void testGetGestor() throws SQLException {
        System.out.println("getGestor");
        Colaborador colab = new Colaborador("Poupas", "219384756", "cenas@gmail.pt");
        EnderecoPostal end = new EnderecoPostal("rua", "1234", "senhora da hora");
        Organizacao instance = new Organizacao("Blip", "500123321", "www.blip.pt", "223456789", "blip@blip.pt", end, colab);
        Colaborador expResult = colab;
        Colaborador result = instance.getGestor();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Organizacao.
     */
    @Test
    public void testEquals() throws SQLException {
        System.out.println("equals");
        Colaborador colab = new Colaborador("Poupas", "219384756", "cenas@gmail.pt");
        EnderecoPostal end = new EnderecoPostal("rua", "1234", "senhora da hora");
        Object o = new Organizacao("Blip", "500123321", "www.blip.pt", "223456789", "blip@blip.pt", end, colab);
        Organizacao instance = new Organizacao("Blip", "500123321", "www.blip.pt", "223456789", "blip@blip.pt", end, colab);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Organizacao.
     */
    @Test
    public void testEquals2() throws SQLException {
        System.out.println("equals");
        Colaborador colab = new Colaborador("Poupas", "219384756", "cenas@gmail.pt");
        EnderecoPostal end = new EnderecoPostal("rua", "1234", "senhora da hora");
        Object o = new Organizacao("Blip", "500123321", "www.blip.pt", "223456789", "blip@blip.pt", end, colab);
        Organizacao instance = new Organizacao("Blip", "520123321", "www.blip.pt", "223456789", "blip@blip.pt", end, colab);
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of novoColaborador method, of class Organizacao.
     */
    @Test
    public void testNovoColaborador() {
        System.out.println("novoColaborador");
        String nome = "Jose";
        String funcao = "Gestor";
        String telefone = "987654321";
        String email = "jose@blip.pt";
        Colaborador expResult = new Colaborador("Jose", "987654321", "jose@blip.pt");
        Colaborador result = Organizacao.novoColaborador(nome, telefone, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of novoEnderecoPostal method, of class Organizacao.
     */
    @Test
    public void testNovoEnderecoPostal() {
        System.out.println("novoEnderecoPostal");
        String rua = "rua";
        String codPostal = "4450-123";
        String localidade = "Ramalde";
        EnderecoPostal expResult = new EnderecoPostal("rua", "4450-123", "Ramalde");
        EnderecoPostal result = Organizacao.novoEnderecoPostal(rua, codPostal, localidade);
        assertEquals(expResult, result);
    }
}
