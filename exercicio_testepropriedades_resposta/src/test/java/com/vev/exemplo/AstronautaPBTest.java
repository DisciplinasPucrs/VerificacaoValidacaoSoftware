package com.vev.exemplo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.arbitraries.FloatArbitrary;

class AstronautaPBTest {
    static class Candidato {
        float altura;
        float peso;
        float tempoEscadas;

        public Candidato(float peso, float altura, float tempoEscadas) {
            this.altura = altura;
            this.peso = peso;
            this.tempoEscadas = tempoEscadas;
        }

        @Override
        public String toString() {
            return "Candidato [altura=" + altura + ", peso=" + peso + ", tempoEscadas=" + tempoEscadas + "]";
        }
    }

    @Test
    void autorizaThrowsIllegalArgumentExceptionWhenPesoNegativo() {
        assertThatThrownBy(() -> Astronauta.autoriza(-1f, Astronauta.ALTURAMAX, Astronauta.TEMPOMAXESCADAS))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void autorizaThrowsIllegalArgumentExceptionWhenAlturaNegativo() {
        assertThatThrownBy(() -> Astronauta.autoriza(Astronauta.PESOMAX, -1f, Astronauta.TEMPOMAXESCADAS))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void autorizaThrowsIllegalArgumentExceptionWhenTempoNegativo() {
        assertThatThrownBy(() -> Astronauta.autoriza(Astronauta.PESOMAX, Astronauta.ALTURAMAX, -1f))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Provide
    private Arbitrary<Candidato> podemViajar() {
        FloatArbitrary peso = Arbitraries.floats().between(Astronauta.PESOMIN,Astronauta.PESOMAX);
        FloatArbitrary altura = Arbitraries.floats().between(Astronauta.ALTURAMIN,Astronauta.ALTURAMAX);
        FloatArbitrary tempoEscadas = Arbitraries.floats().between(0f, Astronauta.TEMPOMAXESCADAS);
        return Combinators.combine(peso,altura,tempoEscadas).as(Candidato::new);
    }

    @Property
    void testaPodeViajar(@ForAll("podemViajar") Candidato c) {
        boolean rObs = Astronauta.autoriza(c.peso, c.altura, c.tempoEscadas);
        assertTrue(rObs);
    }

    @Provide
    private Arbitrary<Candidato> naoPodemViajarPorPeso() {
        Arbitrary<Float> peso = Arbitraries.oneOf(
            Arbitraries.floats().between(0f, true, Astronauta.PESOMIN, false),
            Arbitraries.floats().greaterThan(Astronauta.PESOMAX));
        FloatArbitrary altura = Arbitraries.floats().between(Astronauta.ALTURAMIN,Astronauta.ALTURAMAX);
        FloatArbitrary tempoEscadas = Arbitraries.floats().between(0f, Astronauta.TEMPOMAXESCADAS);
        return Combinators.combine(peso,altura,tempoEscadas).as(Candidato::new);
    }

    @Property
    void testaNaoPodeViajarPorPeso(@ForAll("naoPodemViajarPorPeso") Candidato c) {
        boolean rObs = Astronauta.autoriza(c.peso, c.altura, c.tempoEscadas);
        assertFalse(rObs);
    }

    @Provide
    private Arbitrary<Candidato> naoPodemViajarPorAltura() {
        FloatArbitrary peso = Arbitraries.floats().between(Astronauta.PESOMIN,Astronauta.PESOMAX);
        Arbitrary<Float> altura = Arbitraries.oneOf(
            Arbitraries.floats().between(0f, true, Astronauta.ALTURAMIN, false),
            Arbitraries.floats().greaterThan(Astronauta.ALTURAMAX));
            FloatArbitrary tempoEscadas = Arbitraries.floats().between(0f, Astronauta.TEMPOMAXESCADAS);
        return Combinators.combine(peso,altura,tempoEscadas).as(Candidato::new);
    }

    @Property
    void testaNaoPodeViajarPorAltura(@ForAll("naoPodemViajarPorAltura") Candidato c) {
        boolean rObs = Astronauta.autoriza(c.peso, c.altura, c.tempoEscadas);
        assertFalse(rObs);
    }

    @Provide
    private Arbitrary<Candidato> naoPodemViajarPorEscadas() {
        FloatArbitrary peso = Arbitraries.floats().between(Astronauta.PESOMIN,Astronauta.PESOMAX);
        FloatArbitrary altura = Arbitraries.floats().between(Astronauta.ALTURAMIN,Astronauta.ALTURAMAX);
        FloatArbitrary tempoEscadas = Arbitraries.floats().greaterThan(Astronauta.TEMPOMAXESCADAS);
        return Combinators.combine(peso,altura,tempoEscadas).as(Candidato::new);
    }

    @Property
    void testaNaoPodeViajarPorEscadas(@ForAll("naoPodemViajarPorEscadas") Candidato c) {
        boolean rObs = Astronauta.autoriza(c.peso, c.altura, c.tempoEscadas);
        assertFalse(rObs);
    }
}
