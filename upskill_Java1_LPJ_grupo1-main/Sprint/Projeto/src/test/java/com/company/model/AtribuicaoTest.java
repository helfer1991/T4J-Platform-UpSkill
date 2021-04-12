/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;
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
public class AtribuicaoTest {
    
    public AtribuicaoTest() {
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
     * Test of getId method, of class Atribuicao.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Atribuicao instance = null;
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPickedClass method, of class Atribuicao.
     */
    @Test
    public void testGetPickedClass() {
        System.out.println("getPickedClass");
        Atribuicao instance = null;
        Classificacao expResult = null;
        Classificacao result = instance.getPickedClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataAtribuicao method, of class Atribuicao.
     */
    @Test
    public void testGetDataAtribuicao() {
        System.out.println("getDataAtribuicao");
        Atribuicao instance = null;
        Date expResult = null;
        Date result = instance.getDataAtribuicao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Atribuicao.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Atribuicao instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Atribuicao.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Atribuicao instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
