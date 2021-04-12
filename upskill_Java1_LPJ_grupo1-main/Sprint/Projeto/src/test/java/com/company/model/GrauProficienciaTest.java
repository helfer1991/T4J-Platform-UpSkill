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
public class GrauProficienciaTest {
    
    public GrauProficienciaTest() {
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
     * Test of equals method, of class GrauProficiencia.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a1);
        Object o = new GrauProficiencia("1", "fraco", c1);
        GrauProficiencia instance = new GrauProficiencia("1", "fraco", c1);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
}
