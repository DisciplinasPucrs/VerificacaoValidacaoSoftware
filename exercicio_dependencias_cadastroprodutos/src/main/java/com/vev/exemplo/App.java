package com.vev.exemplo;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException{
        CadastroProduto
        .getInstance()
        .todos()
        .forEach(System.out::println);
    }
}
