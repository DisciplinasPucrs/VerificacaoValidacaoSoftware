package com.vev.exemplo.carrinhocompras;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LeitorDeProdutos implements ILeitorDeProdutos{
    private String fname;
    
    public LeitorDeProdutos(String fname){
        this.fname = fname;
    }

    public List<Produto> carregaProdutos() {
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir +"/" + fname;
        Path path = Paths.get(nameComplete);
        List<Produto> produtos = new LinkedList<>();
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            while (sc.hasNext()) {
                String linha = sc.nextLine();
                String[] dados = linha.split(",");
                int codigo = Integer.parseInt(dados[0]);
                String descricao = dados[1];
                float preco = (float)Double.parseDouble(dados[2]);
                String moeda = dados[3];
                Produto p = new Produto(codigo, descricao, preco, moeda);
                produtos.add(p);
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
        return produtos;
    }    
}
