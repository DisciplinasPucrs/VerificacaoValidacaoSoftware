package com.vev.exemplo.carrinhocompras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Carrinho {
    private List<ItemDeCarrinho> itens;

    public Carrinho(){
        itens = new LinkedList<>();
    }

    public void novoItem(ItemDeCarrinho item){
        itens.add(item);
    }

    public int nroItens() {
        return itens.size();
    }

    public Stream<ItemDeCarrinho> getItensStream() {
        return itens.stream();
    }
}
