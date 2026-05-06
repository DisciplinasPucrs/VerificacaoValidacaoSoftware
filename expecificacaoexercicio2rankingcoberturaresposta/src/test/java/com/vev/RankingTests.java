package com.vev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Obs.: nem todos os casos de teste foram implementados

class RankingTests {
    private Ranking ranking;

    @BeforeEach()
    void setUp() {
        ranking = new Ranking();
    }

    @Test
    void getScoreIndiceValido(){
        Record record = new Record("a",1);
        ranking.add(record);
        Record atual = ranking.getScore(0);
        assertNotNull(atual);
    }

    @Test
    void getScoreIndiceInvalido(){
        Record atual = ranking.getScore(0);
        assertNull(atual);
    }

    @Test
    void numRecordsVazio() {
        int esperado = 0;
        int atual = ranking.numRecords();
        assertEquals(esperado, atual);
    }

    @Test
    void numRecordsUnitario() {
        Record record = new Record("a",1);
        ranking.add(record);
        int esperado = 1;
        int atual = ranking.numRecords();
        assertEquals(esperado, atual);
    }

    @Test
    void numRecordsVarios() {
        ranking.add(new Record("a",1));
        ranking.add(new Record("b",2));
        int esperado = 2;
        int atual = ranking.numRecords();
        assertEquals(esperado, atual);
    }

    @Test
    void addRankingVazio(){
        Record record = new Record("a",1);
        boolean atual = ranking.add(record);
        //verificar se o retorno é verdadeiro
        assertTrue(atual);
        //verificar se a quantidade aumentou corretamente
        assertEquals(1,ranking.numRecords());
        //verificar se a ordenação está correta dos rankings
        assertEquals(record, ranking.getScore(0));
        assertEquals(record,ranking.bestScore());
        assertEquals(record,ranking.worstScore());
    }
}
