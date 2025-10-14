package com.vev.exemplo.carrinhocompras;

import java.util.ArrayList;
import java.util.List;

public class CadastroProduto{
    private List<Produto> produtos;

    public CadastroProduto(ILeitorDeProdutos leitorDeProdutos) {
        this.produtos = leitorDeProdutos.carregaProdutos();
    }

    public Produto recuperaPorCodigo(int codigo) {
        return produtos.stream().filter(prod -> prod.getCodigo() == codigo).findAny().orElse(null);
    }

    public List<Produto> todos(){
        return new ArrayList<>(produtos);
    }

    public List<Produto> produtosVendidosEm(String moeda){
        return produtos.stream().filter(prod -> prod.getMoeda().equals(moeda)).toList();
    }

    public List<Produto> produtosMaisBaratosQue(float valor){
        return produtos.stream().filter(prod -> prod.getPrecoUnitario() < valor).toList();
    }
}
