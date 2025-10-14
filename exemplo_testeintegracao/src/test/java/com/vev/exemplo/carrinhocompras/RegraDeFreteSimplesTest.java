package com.vev.exemplo.carrinhocompras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RegraDeFreteSimplesTest {
    /* 
    private ILeitorDeProdutos cadastroProduto;

    @BeforeAll
    public void inicializa(){
        this.cadastroProduto = new LeitorDeProdutosFAKE();
    }
    */
    @ParameterizedTest
    @CsvSource({"0,0","1,5","3,5","4,12.5","10,12.5","11,20"})
    void testaCarrinhoComDoisItens(int qtdade,double rEsp){
        // Cria um Stub de Carrinho
        Carrinho carrinho = mock(Carrinho.class);
        when(carrinho.nroItens()).thenReturn(qtdade);
        // Cria a regra 
        RegraDeFreteSimples rfs = new RegraDeFreteSimples();
        // Testa o resultado informando o stub
        assertEquals(rEsp, rfs.custoAdicional(carrinho),0.0001);
    }
}
