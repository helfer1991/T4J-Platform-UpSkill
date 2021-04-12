/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

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
public class ColaboradorTest {
    
    public ColaboradorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of hasEmail method, of class Colaborador.
     */
    @Test
    public void testHasEmail() {
        System.out.println("hasEmail");
        String email = "jose@mindera.pt";
        Colaborador instance = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        boolean expResult = true;
        boolean result = instance.hasEmail(email);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hasEmail method, of class Colaborador.
     */
    @Test
    public void testHasEmail2() {
        System.out.println("hasEmail");
        String email = "poupas@mindera.pt";
        Colaborador instance = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        boolean expResult = false;
        boolean result = instance.hasEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Colaborador.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Colaborador c = new Colaborador("Poupas", "1232131", "123@123.pt");
        Colaborador instance = new Colaborador("Oi", "3123123", "contigo@contigo.pt");
        boolean expResult = false;
        boolean result = instance.equals(c);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Colaborador.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Colaborador c = new Colaborador("Poupas", "1232131", "123@123.pt");
        Colaborador instance = new Colaborador("Poupas", "1232131", "123@123.pt");
        boolean expResult = true;
        boolean result = instance.equals(c);
        assertEquals(expResult, result);
    }
    
}
