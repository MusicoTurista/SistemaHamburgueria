package test;

import main.cardapio.LancheBase;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import main.notificacao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.helper.CanalCaptura;

import static org.junit.jupiter.api.Assertions.*;

public class EventoPedidoTest {

    private Pedido pedido;

    @BeforeEach
    void setup() {
        pedido = new PedidoBuilder().cliente("Maria").item(new LancheBase("X", 20.0)).build();
    }

    @Test
    void deveNotificarAoConfirmar() {
        CanalCaptura canal = new CanalCaptura();
        new PedidoConfirmado(canal).notificar(pedido);

        assertFalse(canal.getMensagens().isEmpty());
        assertTrue(canal.ultima().contains(pedido.getId()));
        assertEquals("Maria", canal.getUltimoDestinatario());
    }

    @Test
    void deveNotificarAoFicarPronto() {
        CanalCaptura canal = new CanalCaptura();
        new PedidoProntoEvento(canal).notificar(pedido);

        assertFalse(canal.getMensagens().isEmpty());
    }

    @Test
    void mesmoEventoDeveFuncionarEmCanaisDiferentes() {
        CanalCaptura c1 = new CanalCaptura();
        CanalCaptura c2 = new CanalCaptura();

        new PedidoConfirmado(c1).notificar(pedido);
        new PedidoConfirmado(c2).notificar(pedido);

        assertFalse(c1.getMensagens().isEmpty());
        assertFalse(c2.getMensagens().isEmpty());
        assertEquals(c1.ultima(), c2.ultima());
    }

    @Test
    void deveNotificarSemException() {
        Pedido p = pedido;
        assertAll(() -> assertDoesNotThrow(() -> new PedidoConfirmado(new CanalWhatsapp()).notificar(p)), () -> assertDoesNotThrow(() -> new PedidoConfirmado(new CanalSMS()).notificar(p)), () -> assertDoesNotThrow(() -> new PedidoConfirmado(new DisplayBalcao()).notificar(p)));
    }
}
