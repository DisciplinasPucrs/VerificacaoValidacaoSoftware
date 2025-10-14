package com.vev.exemplo.carrinhocompras;

import java.util.List;

public class CalculadoraDePreco {
    private List<IRegraDeCustoAdicional> regras;

    public CalculadoraDePreco(List<IRegraDeCustoAdicional> regras){
        this.regras = regras;
    }

    public double calculaCustoBasico(Carrinho carrinho){
        return carrinho.getItensStream()
            .mapToDouble(i->i.getProduto().getPrecoUnitario()*i.getQuantidade())
            .sum();
    }

    public double calculaCustoAdicional(Carrinho carrinho){
        return regras.stream()
        .mapToDouble(r->r.custoAdicional(carrinho))
        .sum();
    }

    public double calculaCustoFinal(Carrinho carrinho){
        return calculaCustoBasico(carrinho)+calculaCustoAdicional(carrinho);
    }
}
