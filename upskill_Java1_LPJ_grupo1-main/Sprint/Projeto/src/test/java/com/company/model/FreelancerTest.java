/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
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
public class FreelancerTest {
    
    public FreelancerTest() {
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
     * Test of equals method, of class Freelancer.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");        
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        EnderecoPostal end = new EnderecoPostal("rua", "1234-123", "Matosinhos");
        ArrayList<HabilitacaoAcademica> lHabAc = new ArrayList<>();
        lHabAc.add(new HabilitacaoAcademica("Licenciatura", "EI", "Isep", 15));
        ArrayList<ReconhecimentoCT> lRecFree = new ArrayList<>();
        Date dataRec = Date.valueOf("2012-05-02");
        lRecFree.add(new ReconhecimentoCT(dataRec, new GrauProficiencia("1", "fraco", c1)));
        Freelancer instance = new Freelancer("Jose", "11111111", "jose@jose.pt", "12334322", end, lHabAc, lRecFree);
        Object o = new Freelancer("Jose", "11111111", "jose@jose.pt", "12334322", end, lHabAc, lRecFree);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
}
