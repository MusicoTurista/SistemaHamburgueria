package test;

import main.atendimento.AtendimentoFacade;
import main.caixa.SessaoCaixaSnapshot;
import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import main.cardapio.flyweight.IngredientePool;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.helper.CanalCaptura;

import static org.junit.jupiter.api.Assertions.*;

public class AtendimentoFacadeTest {
    private AtendimentoFacade facade;
    private CanalCaptura canal;

    @BeforeEach
    void setup() {
        canal = new CanalCaptura();
        facade = new AtendimentoFacade(canal);
        IngredientePool.repor("Acém",1000);
        IngredientePool.repor("Wagyu",1000);
    }

    private Pedido pedidoSimples() {
        return new PedidoBuilder().cliente("Maria").item(new LancheBase("X-Duplo", 26.0)).item(new ItemAvulso("Refrigerante", 6.0)).pagamento("credito").build();
    }

    @Test
    void deveAceitarPedidoBalcaoValido() {
        Pedido p = pedidoSimples();
        assertTrue(facade.processarPedido(p, null, 0.0));
    }

    @Test
    void deveAceitarPedidoDeliveryValido() {
        Pedido p = new PedidoBuilder().cliente("Carlos").item(new LancheBase("Wagyu", 58.0)).delivery("Rua das Flores, 42").pagamento("pix").build();
        assertTrue(facade.processarPedido(p, "DELIVERY_DESCONTO_10PCT", 3.0));
    }

    @Test
    void deveRejeitarPedidoVazio() {
        Pedido p = new PedidoBuilder().cliente("X").build();
        assertFalse(facade.processarPedido(p, null, 0.0));
    }

    @Test
    void deveRejeitarPedidoAbaixoDoMinimo() {
        Pedido p = new PedidoBuilder().cliente("X").item(new ItemAvulso("Bala", 0.50)).build();
        assertFalse(facade.processarPedido(p, null, 0.0));
    }

    @Test
    void deveManterSessaoAtualizada() {
        facade.processarPedido(pedidoSimples(), null, 0.0);
        SessaoCaixaSnapshot snap = facade.getSessao().salvar();
        assertEquals(1, snap.getNumPed());
    }

    @Test
    void deveNotificarCliente() {
        facade.processarPedido(pedidoSimples(), null, 0.0);
        assertFalse(canal.getMensagens().isEmpty());
    }

    @Test
    void deveProcessarMultiplosPedidos() {
        for (int i = 0; i < 3; i++) {
            Pedido p = new PedidoBuilder().cliente("Cliente" + i).item(new LancheBase("X", 20.0 + i)).build();
            assertTrue(facade.processarPedido(p, null, 0.0));
        }
        assertEquals(3, facade.getSessao().salvar().getNumPed());
    }
}
