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
public class HabilitacaoAcademicaTest {
    
    public HabilitacaoAcademicaTest() {
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
     * Test of equals method, of class HabilitacaoAcademica.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new HabilitacaoAcademica("Licenciatura", "EI", "ISEP", 15);
        HabilitacaoAcademica instance = new HabilitacaoAcademica("Licenciatura", "EI", "ISEP", 15);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class HabilitacaoAcademica.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object o = new HabilitacaoAcademica("Licenciatura", "EI", "ISEP", 12);
        HabilitacaoAcademica instance = new HabilitacaoAcademica("Licenciatura", "EI", "ISEP", 15);
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
}
