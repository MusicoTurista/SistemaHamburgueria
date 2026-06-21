package test;

import main.cardapio.LancheBase;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import main.estado.ContextoPedido;
import main.estado.NotificadorCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.helper.CanalCaptura;
import test.helper.RegistradorEstados;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {

    private Pedido pedido;

    @BeforeEach
    void setup() {
        pedido = new PedidoBuilder().cliente("T").item(new LancheBase("X", 20.0)).build();
    }

    @Test
    void observadorDeveSerNotificado() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        RegistradorEstados obs = new RegistradorEstados();
        ctx.inscrever(obs);

        ctx.confirmar();
        assertTrue(obs.getEstados().contains("CONFIRMADO"));
    }

    @Test
    void deveNotificarMultiplosObservadores() {
        ContextoPedido ctx = new ContextoPedido(pedido);
        RegistradorEstados obs1 = new RegistradorEstados();
        RegistradorEstados obs2 = new RegistradorEstados();
        ctx.inscrever(obs1);
        ctx.inscrever(obs2);

        ctx.confirmar();
        assertEquals(obs1.getEstados(), obs2.getEstados());
    }

    @Test
    void deveNotificadorClientePronto() {
        CanalCaptura canal = new CanalCaptura();
        NotificadorCliente nc = new NotificadorCliente(canal);

        nc.aoMudarEstado(pedido, "PRONTO");
        assertTrue(canal.ultima().contains("pronto"));
    }

    @Test
    void naoDeveNotificarEmPreparo() {
        CanalCaptura canal = new CanalCaptura();
        NotificadorCliente nc = new NotificadorCliente(canal);

        nc.aoMudarEstado(pedido, "EM PREPARO");
        assertTrue(canal.getMensagens().isEmpty());
    }
}
