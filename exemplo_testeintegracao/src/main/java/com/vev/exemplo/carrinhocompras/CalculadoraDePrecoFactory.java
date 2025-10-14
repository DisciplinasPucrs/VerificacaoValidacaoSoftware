package com.vev.exemplo.carrinhocompras;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraDePrecoFactory {
    public static CalculadoraDePreco freteSimplesDolarBuild(){
        List<IRegraDeCustoAdicional> regras = new ArrayList<>(2);
        regras.add(new RegraDeFreteSimples());
        regras.add(new RegraDoDolar());
        return new CalculadoraDePreco(regras);
    }    
}
