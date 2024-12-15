import br.ufrn.imd.dados.Catalogo;
import br.ufrn.imd.excecoes.ProdutoDuplicadoException;
import br.ufrn.imd.excecoes.ProdutoNaoEncontradoException;
import br.ufrn.imd.modelo.Pedido;
import br.ufrn.imd.modelo.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CatalogoTest {

    private Catalogo catalogo;

    @BeforeEach
    public void setUp() {
        catalogo = new Catalogo();
    }


    @Test
    public void deveAdicionarProdutoTest() {
        Produto produto = new Produto(1, "Produto 1", 10.00f);
        try {
            catalogo.adicionarProduto(produto);
        } catch (Exception e) {
            fail();
        }

        assertEquals(1, catalogo.listarProdutos().size());
        assertEquals(produto, catalogo.listarProdutos().get(0));
    }

    @Test
    public void naoDeveAdicionarProdutoComIdDuplicadoTest() {
        Produto produto = new Produto(1, "Produto 1", 10.00f);
        try {
            catalogo.adicionarProduto(produto);
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, catalogo.listarProdutos().size());
        assertEquals(produto, catalogo.listarProdutos().get(0));

        Produto produto2 = new Produto(1, "Produto 2", 20.00f);
        assertThrows(ProdutoDuplicadoException.class, () -> {
            catalogo.adicionarProduto(produto2);
        });

        assertEquals(1, catalogo.listarProdutos().size());
        assertEquals(produto, catalogo.listarProdutos().get(0));
    }

    @Test
    public void deveBuscarProdutoPorIdTest() {
        Produto produto = new Produto(1, "Produto 1", 10.00f);
        try {
            catalogo.adicionarProduto(produto);
        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals(produto, catalogo.buscarProdutoPorId(1));
        } catch (ProdutoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    public void deveBuscarProdutoPorIdNegativoComErroTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            catalogo.buscarProdutoPorId(-1);
        });
    }

    @Test
    void deveCalcularTotalSemDescontoTest() {
        Pedido pedido = new Pedido("Cliente 1");
        Produto produto1 = new Produto(1, "Produto 1", 40.0f);
        Produto produto2 = new Produto(2, "Produto 2", 50.0f);

        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        assertEquals(90.0f, pedido.calcularTotal());
    }

    @Test
    void deveCalcularTotalComDescontoTest() {
        Pedido pedido = new Pedido("Cliente 1");
        Produto produto1 = new Produto(1, "Produto 1", 70.0f);
        Produto produto2 = new Produto(2, "Produto 2", 40.0f);

        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Total: 110 - 10% desconto = 99
        assertEquals(99.0f, pedido.calcularTotal());
    }

    @Test
    void deveCalcularTotalComDescontoSemProdutosTest() {
        assertDoesNotThrow(() -> {
            Catalogo catalogo = new Catalogo();
            Produto produto1 = new Produto(1, "Produto 1", 30.0f);
            Produto produto2 = new Produto(2, "Produto 2", 80.0f);

            catalogo.adicionarProduto(produto1);
            catalogo.adicionarProduto(produto2);

            Pedido pedido = new Pedido("Cliente 1");
            pedido.adicionarProduto(catalogo.buscarProdutoPorId(1));
            pedido.adicionarProduto(catalogo.buscarProdutoPorId(2));

            assertEquals(99.0f, pedido.calcularTotal());
        });
    }
}
