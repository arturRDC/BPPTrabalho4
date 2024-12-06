package br.ufrn.imd.modelo;

import java.util.List;

public class Pedido {
    private List<Produto> produtos;
    private String cliente;

    public Pedido() {
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public float calcularTotal() {
        float valorTotal = 0.0f;
        for (Produto produto : produtos) {
            valorTotal += produto.preco;
        }

        // Desconto de 10% se o total for maior que 100
        if (valorTotal > 100.00f) {
            return (float) (valorTotal * 0.9);
        } else {
            return valorTotal;
        }
    }

}
