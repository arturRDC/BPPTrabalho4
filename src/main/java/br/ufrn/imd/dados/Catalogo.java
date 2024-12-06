package br.ufrn.imd.dados;

import br.ufrn.imd.modelo.Produto;

import java.util.List;

public class Catalogo {
    private List<Produto> produtos;

    public Catalogo() {
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        return produtos.stream().filter(p -> p.id == id).findFirst().orElse(null);
    }
}
