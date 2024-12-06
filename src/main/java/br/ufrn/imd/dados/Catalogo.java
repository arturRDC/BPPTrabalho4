package br.ufrn.imd.dados;

import br.ufrn.imd.excecoes.ProdutoDuplicadoException;
import br.ufrn.imd.excecoes.ProdutoNaoEncontradoException;
import br.ufrn.imd.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Produto> produtos;

    public Catalogo() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) throws ProdutoDuplicadoException {
        for (Produto p : produtos) {
            if (p.id == produto.id) {
                throw new ProdutoDuplicadoException(produto.id);
            }
        }
        produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) throws ProdutoNaoEncontradoException {
        if (id < 0) {
            throw new IllegalArgumentException("Id deve ser maior que zero");
        }
        return produtos.stream().filter(p -> p.id == id).findFirst().orElseThrow(()->new ProdutoNaoEncontradoException(id));
    }
}
