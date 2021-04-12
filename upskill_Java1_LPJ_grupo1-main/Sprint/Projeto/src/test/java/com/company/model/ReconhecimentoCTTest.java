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
public class ReconhecimentoCTTest {
    
    public ReconhecimentoCTTest() {
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
     * Test of equals method, of class ReconhecimentoCT.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        Date dataRec = Date.valueOf("2012-05-02");
        GrauProficiencia gp = new GrauProficiencia("1", "fraco", c1);
        Object o = new ReconhecimentoCT(dataRec, gp);
        ReconhecimentoCT instance = new ReconhecimentoCT(dataRec, gp);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
}
