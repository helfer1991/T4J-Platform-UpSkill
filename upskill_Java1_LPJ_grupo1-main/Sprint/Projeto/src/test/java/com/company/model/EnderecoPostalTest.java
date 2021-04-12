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
public class EnderecoPostalTest {
    
    public EnderecoPostalTest() {
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
     * Test of equals method, of class EnderecoPostal.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        EnderecoPostal end = new EnderecoPostal("rua", "1234-333", "senhora");
        EnderecoPostal instance = new EnderecoPostal("rua sesamo", "4321-333", "cenas");
        boolean expResult = false;
        boolean result = instance.equals(end);
        assertEquals(expResult, result);
    }

        /**
     * Test of equals method, of class EnderecoPostal.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        EnderecoPostal end = new EnderecoPostal("rua", "1234-333", "senhora da hora");
        EnderecoPostal instance = new EnderecoPostal("rua", "1234-333", "senhora da hora");
        boolean expResult = true;
        boolean result = instance.equals(end);
        assertEquals(expResult, result);
    }
    
}
