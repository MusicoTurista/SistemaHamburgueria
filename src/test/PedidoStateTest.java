package test;

import main.cardapio.LancheBase;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import main.estado.ContextoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.helper.RegistradorEstados;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoStateTest {
    private Pedido pedido;

    @BeforeEach
    void setup() {
        pedido = new PedidoBuilder()
                .cliente("T")
                .item(new LancheBase("X", 20.0))
                .build();
    }

    @Test
    void estadoInicialDeveSerAguardandoPagamento() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        assertEquals("AGUARDANDO PAGAMENTO", ctx.estadoAtual());
    }

    @Test
    void deveConfirmarPedido() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        ctx.confirmar();
        assertEquals("CONFIRMADO", ctx.estadoAtual());
    }

    @Test
    void devePercorrerTodosOsEstados() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        RegistradorEstados obs = new RegistradorEstados();
        ctx.inscrever(obs);

        ctx.confirmar();
        ctx.iniciarPreparo();
        ctx.finalizar();
        ctx.entregar();

        assertEquals(List.of("CONFIRMADO", "EM PREPARO", "PRONTO", "ENTREGUE"), obs.getEstados());
    }

    @Test
    void naoDevePermitirTransicaoInvalida() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        ctx.iniciarPreparo(); // inválido nesse estado
        assertEquals("AGUARDANDO PAGAMENTO", ctx.estadoAtual()); // estado preservado
    }

    @Test
    void estadoFinalNaoDeveMudar() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        ctx.confirmar(); ctx.iniciarPreparo(); ctx.finalizar(); ctx.entregar();
        assertEquals("ENTREGUE", ctx.estadoAtual());

        ctx.confirmar();
        assertEquals("ENTREGUE", ctx.estadoAtual());
    }
}
