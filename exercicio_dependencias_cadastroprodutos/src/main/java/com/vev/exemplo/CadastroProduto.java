package com.vev.exemplo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroProduto{
    private static CadastroProduto cp = null;
    private static final String FNAME = "produtos.dat";
    private List<Produto> produtos;

    private CadastroProduto() throws IOException {
        this.produtos = new ArrayList<>();
        carregaProdutos();
    }

    public static CadastroProduto getInstance() throws IOException {
        if (cp == null) {
            cp = new CadastroProduto();
        }
        return cp;
    }

    public void carregaProdutos() throws IOException {
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir +"/" + FNAME;
        Path path = Paths.get(nameComplete);
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
            throw x;
        }
    }

    public Produto recuperaPorCodigo(int codigo) {
        return produtos.stream().filter(prod -> prod.getCodigo() == codigo).findAny().orElse(null);
    }

    public List<Produto> todos(){
        return new ArrayList<>(produtos);
    }

    public List<Produto> produtosVendidosEm(String moeda){
        return produtos.stream().filter(prod -> prod.getMoeda().equals(moeda)).toList();
    }

    public List<Produto> produtosMaisBaratosQue(float valor){
        return produtos.stream().filter(prod -> prod.getPrecoUnitario() < valor).toList();
    }
}
