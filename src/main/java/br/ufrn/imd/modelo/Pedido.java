package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Produto> produtos;
    private String cliente;

    public Pedido(String cliente) {
        produtos = new ArrayList<>();
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public float calcularTotal() {
        float valorTotal = somarValores();
        valorTotal = calcularDesconto(valorTotal);

        return valorTotal;
    }

    private static float calcularDesconto(float valorTotal) {
        // Desconto de 10% se o total for maior que 100
        if (valorTotal > 100.00f) {
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
