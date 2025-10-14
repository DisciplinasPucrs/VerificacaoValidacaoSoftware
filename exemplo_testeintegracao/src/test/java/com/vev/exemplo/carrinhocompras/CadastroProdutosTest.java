package com.vev.exemplo.carrinhocompras;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class CadastroProdutosTest {
    @Test
    void produtosVendidosEmTest(){
        CadastroProduto cp = new CadastroProduto(new LeitorDeProdutosFAKE());
        Collection<Produto> prods = cp.produtosVendidosEm("Real");
        assertEquals(3,prods.size());
    }
}
