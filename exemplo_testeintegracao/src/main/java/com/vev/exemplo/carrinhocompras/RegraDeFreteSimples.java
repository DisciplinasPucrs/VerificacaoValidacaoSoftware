package com.vev.exemplo.carrinhocompras;

public class RegraDeFreteSimples implements IRegraDeCustoAdicional{
    @Override
    public double custoAdicional(Carrinho carrinho) {
        int qtdadeItens = carrinho.nroItens();

        if(qtdadeItens == 0) return 0;
        if(qtdadeItens >= 1 && qtdadeItens <= 3) return 5;
        if(qtdadeItens >= 4 && qtdadeItens <= 10) return 12.5;
        return 20.0;
    }
}
