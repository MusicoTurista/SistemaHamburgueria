package test;

import main.cardapio.ItemAvulso;
import main.config.Loja;
import main.dominio.Pedido;
import main.cardapio.LancheBase;
import main.dominio.PedidoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderPedidoTest {
    @Test
    void deveRetornarNomeDoCliente() {
        Pedido p = new PedidoBuilder().cliente("Ana").item(new LancheBase("X-Burguer", 25.0)).pagamento("pix").build();

        assertEquals("Ana", p.getNomeCliente());
    }
    @Test
    void deveRetornarMetodoDePagamento() {
        Pedido p = new PedidoBuilder().cliente("Ana").item(new LancheBase("X-Burguer", 25.0)).pagamento("pix").build();

        assertEquals("pix", p.getFormaPagamento());
    }
    @Test
    void deveRetornarTamanhoDoPedido() {
        Pedido p = new PedidoBuilder().cliente("Ana").item(new LancheBase("X-Burguer", 25.0)).pagamento("pix").build();

        assertEquals(1, p.getItens().size());
    }
    @Test
    void devePermitirPedidoSemDelivery() {
        Pedido p = new PedidoBuilder().cliente("Ana").item(new LancheBase("X-Burguer", 25.0)).pagamento("pix").build();

        assertFalse(p.getDelivery());
    }

    @Test
    void devePermitirPedidoComDelivery() {
        Pedido p = new PedidoBuilder().cliente("Bob").item(new ItemAvulso("Refri", 6.0)).delivery("Rua A, 100").build();

        assertTrue(p.getDelivery());
    }
    @Test
    void deveRetornarEnderecoDelivery() {
        Pedido p = new PedidoBuilder().cliente("Bob").item(new ItemAvulso("Refri", 6.0)).delivery("Rua A, 100").build();

        assertEquals("Rua A, 100", p.getEnderecoEntrega());
    }

    @Test
    void deveSomarTodosOsItens() {
        Pedido p = new PedidoBuilder().cliente("C").item(new LancheBase("Lanche", 20.0)).item(new ItemAvulso("Bebida", 6.0)).build();

        assertEquals(26.0, p.subtotal().reais(), 0.001);
    }

    @Test
    void deveAplicarTaxaAoValorTotal() {
        Pedido p = new PedidoBuilder().cliente("D").item(new LancheBase("X", 100.0)).build();

        double esperado = 100.0 * (1 + Loja.getInstance().getTaxaServico());
        assertEquals(esperado, p.totalComTaxa().reais(), 0.01);
    }

    @Test
    void pedidoPossuiIdUnico() {
        Pedido p1 = new PedidoBuilder().cliente("E").item(new ItemAvulso("X", 1.0)).build();
        Pedido p2 = new PedidoBuilder().cliente("F").item(new ItemAvulso("X", 1.0)).build();

        assertNotEquals(p1.getId(), p2.getId());
    }

    @Test
    void listaDeItensDeveSerImutavel() {
        Pedido p = new PedidoBuilder().cliente("G").item(new ItemAvulso("X", 1.0)).build();

        assertThrows(UnsupportedOperationException.class, () -> p.getItens().add(new ItemAvulso("Y", 2.0)));
    }
}
