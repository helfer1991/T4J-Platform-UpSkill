/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.sql.Date;
import java.util.ArrayList;
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
public class ClassificacaoTest {
    
    public ClassificacaoTest() {
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
     * Test of equals method, of class Classificacao.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        List<CaraterCT> lCT = new ArrayList<>();        
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, lCT);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        String ref = "123457";
        Tarefa taf = new Tarefa(ref, "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);
        TipoRegimento reg = new TipoRegimento("1", "Oleole");
        Date dtInicioP = Date.valueOf("2020-01-01");
        Date dtFimP = Date.valueOf("2020-12-12");
        Date dtInicioC = Date.valueOf("2020-01-20");
        Date dtFimC = Date.valueOf("2020-01-22");
        Date dtInicioS = Date.valueOf("2020-02-02");
        Date dtFimS = Date.valueOf("2020-03-03");
        Anuncio anun = new Anuncio(dtInicioP, dtFimP, dtInicioC, dtFimC, dtInicioS, dtFimS, taf, c, reg);
        EnderecoPostal end = new EnderecoPostal("rua", "1234-123", "Matosinhos");
        ArrayList<HabilitacaoAcademica> lHabAc = new ArrayList<>();
        lHabAc.add(new HabilitacaoAcademica("Licenciatura", "EI", "Isep", 15));
        ArrayList<ReconhecimentoCT> lRecFree = new ArrayList<>();
        Date dataRec = Date.valueOf("2012-05-02");
        lRecFree.add(new ReconhecimentoCT(dataRec, new GrauProficiencia("1", "fraco", c1)));
        Freelancer free = new Freelancer("Jose", "11111111", "jose@jose.pt", "12334322", end, lHabAc, lRecFree);
        Candidatura cand = new Candidatura(anun, Date.valueOf("2020-12-12"), 12, 4, "ddddddddddddddd", "dddddddddddddddd", free);
        Object o = new Classificacao(1, Date.valueOf("2020-01-02"), cand);
        Classificacao instance = new Classificacao(1, Date.valueOf("2020-01-02"), cand);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    
}
