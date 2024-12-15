package br.ufrn.imd.negocio;

import br.ufrn.imd.excecoes.ProdutoDuplicadoException;
import br.ufrn.imd.excecoes.ProdutoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Catalogo {
    private List<Produto> produtos;
    private static final Logger logger = Logger.getLogger(Catalogo.class.getName());

    public Catalogo() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) throws ProdutoDuplicadoException {
        for (Produto p : produtos) {
            if (p.id == produto.id) {
                logger.warning("Produto já existe no catálogo");
                throw new ProdutoDuplicadoException(produto.id);
            }
        }
        produtos.add(produto);
        logger.info("Produto adicionado ao catálogo com sucesso");
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
