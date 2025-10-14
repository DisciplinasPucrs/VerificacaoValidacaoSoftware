package com.vev.exemplo.carrinhocompras;

import java.util.ArrayList;
import java.util.List;

public class LeitorDeProdutosFAKE implements ILeitorDeProdutos {

    @Override
    public List<Produto> carregaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(100,"Banana",2.5F,"Dolar"));
        produtos.add(new Produto(120,"Maca",4.5F,"Dolar"));
        produtos.add(new Produto(105,"Laranja",1.2F,"Real"));
        produtos.add(new Produto(135,"Limao",0.8F,"Real"));
        produtos.add(new Produto(124,"Roma",1.18F,"Euro"));
        produtos.add(new Produto(200,"Abacate",2.5F,"Dolar"));
        produtos.add(new Produto(220,"Groselha",4.5F,"Peso"));
        produtos.add(new Produto(205,"Mamao",1.2F,"Dolar"));
        produtos.add(new Produto(235,"Melao",0.8F,"Real"));
        produtos.add(new Produto(224,"Uva",1.18F,"Euro"));
        produtos.add(new Produto(250,"Bergamota",1.18F,"Euro"));
        return produtos;
    }
}




