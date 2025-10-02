package com.vev.exemplo;

public class App {
    public static void main(String[] args) {
        (new CadastroProduto(new LeitorDeProdutosArquivo("produtos.dat")))
        .todos()
        .forEach(System.out::println);
    }
}
