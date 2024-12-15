package br.ufrn.imd.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Pedido {
    private List<Produto> produtos;
    private String cliente;
    private static final Logger logger = Logger.getLogger(Pedido.class.getName());

    public Pedido(String cliente) {
        produtos = new ArrayList<>();
        this.cliente = cliente;
        logger.info("Pedido criado com sucesso");
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public float calcularTotal() {
        float valorTotal = somarValores();
        valorTotal = aplicarDesconto(valorTotal);

        return valorTotal;
    }

    private static float aplicarDesconto(float valorTotal) {
        // Desconto de 10% se o total for maior que 100
        if (valorTotal > 100.00f) {
            logger.info("Desconto aplicado com sucesso");
            return (float) (valorTotal * 0.9);
        } else {
            return valorTotal;
        }
    }

    private float somarValores() {
        float valorTotal = 0.0f;
        for (Produto produto : produtos) {
            valorTotal += produto.preco;
        }
        return valorTotal;
    }

}
