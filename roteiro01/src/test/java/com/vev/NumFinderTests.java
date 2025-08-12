package com.vev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumFinderTests {
    private NumFinder nf = null;

    @BeforeEach
    void setUp() {
        nf = new NumFinder();
    }

    @Test
    void testeMaiorQQOrdem() {
        nf.find(new int[] { 10, 8, 2, 14, 7 });
        int maior = nf.getLargest();
        assertEquals(14, maior);
    }

    @Test
    void testeMenorQQOrdem() {
        nf.find(new int[] { 10, 8, 2, 14, 7 });
        int menor = nf.getSmallest();
        assertEquals(2, menor);
    }

    @Test
    void testeMaiorOrdemCrescente() {
        nf.find(new int[] { 1, 2, 3, 4, 5 });
        int maior = nf.getLargest();
        assertEquals(5, maior);
    }

    @Test
    void testeMenorOrdemCrescente() {
        nf.find(new int[] { 1, 2, 3, 4, 5 });
        int menor = nf.getSmallest();
        assertEquals(1, menor);
    }

}
