/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
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
public class CategoriaTarefaTest {
    
    public CategoriaTarefaTest() {
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
     * Test of equals method, of class CategoriaTarefa.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        CaraterCT cCT = new CaraterCT("Webdev", "1", 1, "1");
        List<CaraterCT> lCT = new ArrayList<>();        
        CategoriaTarefa instance = new CategoriaTarefa("Webdev", a, lCT);
        CategoriaTarefa o = new CategoriaTarefa("Webdev", a, lCT);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
//    
//        /**
//     * Test of equals method, of class CategoriaTarefa.
//     */
//    @Test
//    public void testEquals2() {
//        System.out.println("equals");
//        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
//        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
//        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
//        map.put(c1, true);
//        CategoriaTarefa o = new CategoriaTarefa("Webdev", a, map);
//        CategoriaTarefa instance = new CategoriaTarefa("Cloud", a, map);
//        boolean expResult = false;
//        boolean result = instance.equals(o);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getId method, of class CategoriaTarefa.
     */
}
