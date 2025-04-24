package com.vev.exemplo;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

class PropriedadesBasicasTests {
    //Este exemplo é uma propriedade que retorna um valor booleano para o sucesso ou falha
    //Por padrão, irá executar até 1000 vezes, parando na primeira falha encontrada
    @Property
    boolean valorAbsolutoDeTodosNumerosInteirosEhPositivo(@ForAll int umNumero) {
        return Math.abs(umNumero) >= 0;
    }

    //Este exemplo é uma propriedade que retorna void e utiliza asserções para o sucesso ou falha
    //Irá executar até 100 vezes, parando na primeira falha encontrada
    @Property(tries = 100)
    void comprimentoDePalavrasConcatenadasEhMaiorIgualAoComprimentoDeCada(
        @ForAll String string1, @ForAll String string2
    ) {
        String concatenacao = string1 + string2;
        assertThat(concatenacao.length()).isGreaterThan(string1.length());
        assertThat(concatenacao.length()).isGreaterThan(string2.length());
    }
}
