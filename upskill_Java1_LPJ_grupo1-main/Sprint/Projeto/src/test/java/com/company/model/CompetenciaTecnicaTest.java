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
public class CompetenciaTecnicaTest {
    
    public CompetenciaTecnicaTest() {
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
     * Test of hasId method, of class CompetenciaTecnica.
     */
    @Test
    public void testHasId() { 
        System.out.println("hasId");
        String id = "CompTec-1";
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a1);
        boolean expResult = true;
        boolean result = c1.hasId(id);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hasId method, of class CompetenciaTecnica.
     */
    @Test
    public void testHasId2() { 
        System.out.println("hasId");
        String id = "C1";
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a1);
        boolean expResult = false;
        boolean result = c1.hasId(id);
        System.out.println(result);
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class CompetenciaTecnica.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a1);
        CompetenciaTecnica c2 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a1);
        boolean expResult = true;
        boolean result = c1.equals(c2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class CompetenciaTecnica.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        AreaAtividade a2 = new AreaAtividade("2", "Backend", "Backend Engineer");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a1);
        CompetenciaTecnica c2 = new CompetenciaTecnica("2", "Java", "Desenvolvimento aplicacoes em java usando spring boot", a2);
        boolean expResult = false;
        boolean result = c1.equals(c2);
        assertEquals(expResult, result);
    }
    
}
