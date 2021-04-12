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
public class AreaAtividadeTest { // USAR MOCKITO
    
    public AreaAtividadeTest() {
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
     * Test of hasId method, of class AreaAtividade.
     */
    @Test
    public void testHasId() {
        System.out.println("hasId");
        String id = "2";
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        boolean expResult = false;
        boolean result = a1.hasId(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hasId method, of class AreaAtividade.
     */
    @Test
    public void testHasId2() {
        System.out.println("hasId");
        String id = "Aa-1";
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        boolean expResult = true;
        boolean result = a1.hasId(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AreaAtividade.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        AreaAtividade a2 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        boolean expResult = true;
        boolean result = a1.equals(a2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class AreaAtividade.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend Engineer");
        AreaAtividade a2 = new AreaAtividade("2", "Frontend", "Frontend Engineer");
        boolean expResult = false;
        boolean result = a1.equals(a2);
        assertEquals(expResult, result);
    }    
}
