package com.vev.exemplo.carrinhocompras;

public class App {
    public static void main(String[] args){
        CadastroProduto cp = new CadastroProduto(new LeitorDeProdutos("P65_SistemaIntegracao/ExCarrinhoCompras/produtos.dat"));
        
        Carrinho carrinho = new Carrinho();
        carrinho.novoItem(new ItemDeCarrinho(cp.recuperaPorCodigo(120), 20));
        carrinho.novoItem(new ItemDeCarrinho(cp.recuperaPorCodigo(105), 10));
        carrinho.novoItem(new ItemDeCarrinho(cp.recuperaPorCodigo(135), 15));

        CalculadoraDePreco calcPr = CalculadoraDePrecoFactory.freteSimplesDolarBuild();
        System.out.println(carrinho);
        System.out.println("Custo basico:"+calcPr.calculaCustoBasico(carrinho));
        System.out.println("Custo adicional:"+calcPr.calculaCustoAdicional(carrinho));
        System.out.println("Custo basico:"+calcPr.calculaCustoFinal(carrinho));
    }
}
