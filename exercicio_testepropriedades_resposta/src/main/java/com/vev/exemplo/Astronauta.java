package com.vev.exemplo;

// Requisitos para viajar na nave New Shepard da Blue Origin
public class Astronauta {
    public static final float PESOMIN = 50F;
    public static final float PESOMAX = 101F;
    public static final float ALTURAMIN = 1.52F;
    public static final float ALTURAMAX = 1.93F;
    public static final float TEMPOMAXESCADAS = 80.0F; // para subir 90 degraus

    private static boolean entre(float valor, float min, float max) {
        return valor >= min && valor <= max;
    }

    public static boolean autoriza(float peso, float altura, float tempoEscadas) {
        if (peso < 0) throw new IllegalArgumentException("peso não pode ser negativo");
        if (altura < 0) throw new IllegalArgumentException("altura não pode ser negativo");
        if (tempoEscadas < 0) throw new IllegalArgumentException("tempoEscadas não pode ser negativo");
        if (!entre(peso, PESOMIN, PESOMAX)) {
            return false;
        }
        if (!entre(altura, ALTURAMIN, ALTURAMAX)) {
            return false;
        }
        return tempoEscadas <= TEMPOMAXESCADAS;
    }
}
