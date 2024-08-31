package com.vev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//Obs.: nem todos os casos de teste foram implementados

class BarcaTests {
    private Barca barca;

    @BeforeEach
    void setUp() {
        barca = new Barca();
    }

    @Test
    void testaOcupaLugarAssentoOcupado() {
        barca.ocupaLugar(1, 1);
        int esperado = 1;
        int atual = barca.ocupaLugar("F01A01");
        Assertions.assertEquals(esperado, atual);
    }

    @ParameterizedTest
    @CsvSource({
            "F62A01",
            "F32A21",
            "F2A21",
            "F02A1",
            "Z02A19",
            "F03X19"
    })
    void testaOcupaLugarAssentoInvalido(String assento) {
        int esperado = 0;
        int atual = barca.ocupaLugar(assento);
        Assertions.assertEquals(esperado, atual);
    }

    // Método auxiliar: ocupa uma fileira de assentos
    private void ocupaFileira(int f) {
        for (int i = 0; i < Barca.ASSENTOS_POR_FILA; i++) {
            barca.ocupaLugar(f, i);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "F10A18,0,3",
            "F42A10,6,3",
            "F35A01,13,3",
            "F36A17,0,2",
            "F47A16,0,2",
            "F37A15,6,2"
    })
    void testaOcupaLugar(String assento, int qtdadeFilasOcupar, int esperado) {
        // Preparação do estado do objeto
        int filasBaixas = 0;
        int filasAltas = 0;
        if (qtdadeFilasOcupar <= 6) {
            filasBaixas = qtdadeFilasOcupar;
        } else {
            filasBaixas = 6;
            filasAltas = 39 + qtdadeFilasOcupar - 6;
        }
        for (int fila = 0; fila < filasBaixas; fila++) {
            ocupaFileira(fila);
        }
        for (int fila = 40; fila < filasAltas; fila++) {
            ocupaFileira(fila);
        }
        // Ocupar um lugar
        int atual = barca.ocupaLugar(assento);
        Assertions.assertEquals(esperado, atual);
    }
}
