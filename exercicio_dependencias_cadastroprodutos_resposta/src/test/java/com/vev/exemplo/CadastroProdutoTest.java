package com.vev.exemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CadastroProdutoTest {
    @Mock
    private LeitorDeProdutos leitorDeProdutos;
    private CadastroProduto cadastroProduto;

    @Test
    void cadastroProdutoVazio() {
        cadastroProduto = new CadastroProduto(leitorDeProdutos);
        assertNull(cadastroProduto.recuperaPorCodigo(0));
        assertThat(cadastroProduto.todos()).isEmpty();
        assertThat(cadastroProduto.produtosVendidosEm("Real")).isEmpty();
        assertThat(cadastroProduto.produtosMaisBaratosQue(0)).isEmpty();
    }

    @Test
    void cadastroProdutoComUmProduto() {
        Produto produto = new Produto(100,"Banana",2.5F,"Real");
        List<Produto> produtos = Arrays.asList(produto);
        when(leitorDeProdutos.carregaProdutos()).thenReturn(produtos);
        cadastroProduto = new CadastroProduto(leitorDeProdutos);
        assertEquals(100, cadastroProduto.recuperaPorCodigo(100).getCodigo());
        assertThat(cadastroProduto.todos()).hasSize(1);
        assertThat(cadastroProduto.produtosVendidosEm("Real")).hasSize(1);
        assertThat(cadastroProduto.produtosMaisBaratosQue(0)).isEmpty();
        assertThat(cadastroProduto.produtosMaisBaratosQue(5)).hasSize(1);
    }
}
