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
public class TarefaTest {
    
    public TarefaTest() {
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
     * Test of hasRef method, of class Tarefa.
     */
    @Test
    public void testHasRef() {
        System.out.println("hasRef");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        CaraterCT cCT = new CaraterCT("Webdev", "1", 1, "1");
        List<CaraterCT> lCT = new ArrayList<>();        
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, lCT);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        String ref = "123457";
        Tarefa instance = new Tarefa(ref, "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);
        boolean expResult = true;
        boolean result = instance.hasRef(ref);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Tarefa.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        CaraterCT cCT = new CaraterCT("Webdev", "1", 1, "1");
        List<CaraterCT> lCT = new ArrayList<>();        
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, lCT);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        String ref = "123457";
        Tarefa instance = new Tarefa(ref, "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);
        Tarefa t = instance;
        boolean expResult = true;
        boolean result = instance.equals(t);
        assertEquals(expResult, result);
    }    
}
