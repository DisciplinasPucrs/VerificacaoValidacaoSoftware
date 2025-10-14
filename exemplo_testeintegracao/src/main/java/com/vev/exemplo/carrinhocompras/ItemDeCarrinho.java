package com.vev.exemplo.carrinhocompras;

public class ItemDeCarrinho{
    private Produto produto;
    private int quantidade;
    
    public ItemDeCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "ItemDeCarrinho [produto=" + produto + ", quantidade=" + quantidade + "]";
    }
}
