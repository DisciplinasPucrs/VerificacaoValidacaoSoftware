package com.vev.exemplo.carrinhocompras;

public class RegraDoDolar implements IRegraDeCustoAdicional {
    @Override
    public double custoAdicional(Carrinho carrinho) {
        boolean temPrecoEmDolar = carrinho.getItensStream()
            .anyMatch(i->i.getProduto().getMoeda().equals("Dolar"));
        if (temPrecoEmDolar){
            return 7.5;
        }
        return 0.0;
    } 
}
