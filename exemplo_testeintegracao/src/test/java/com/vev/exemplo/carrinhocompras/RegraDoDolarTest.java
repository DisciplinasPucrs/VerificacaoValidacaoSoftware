package com.vev.exemplo.carrinhocompras;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RegraDoDolarTest {    
    @ParameterizedTest
    @CsvSource({"0,0","1,7.5","2,7.5"})
    void testaCarrinhoComDoisItens(int qtdade,double rEsp){
        // Cria um Carrinho sem regras de custo adicional
        Carrinho carrinho = new Carrinho();
        // Cria uma lista de produtos em dolar
        List<Produto> produtos = new ArrayList<>(2);
        produtos.add(new Produto(100,"Banana",2.5F,"Dolar"));
        produtos.add(new Produto(120,"Maca",4.5F,"Dolar"));        
        // Insere no carrinho tantos produtos quantos solicitados
        for(int i=0;i<qtdade;i++){
            carrinho.novoItem(new ItemDeCarrinho(produtos.get(i),1));
        }
        // Cria a regra a ser testada
        RegraDoDolar rd = new RegraDoDolar();
        // Testa o resultado
        assertEquals(rEsp, rd.custoAdicional(carrinho),0.0001);
    }
}