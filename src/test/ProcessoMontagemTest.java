package test;

import main.cardapio.LancheBase;
import main.cozinha.MontadorClassico;
import main.cozinha.MontadorGourmet;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ProcessoMontagemTest {
    private Pedido pedidoBalcao() {
        return new PedidoBuilder().cliente("T").item(new LancheBase("X", 20.0)).build();
    }

    private Pedido pedidoDelivery() {
        return new PedidoBuilder().cliente("T").item(new LancheBase("X", 20.0)).delivery("Rua A, 1").build();
    }

    @Test
    void deveExecutarMontadorClassicoBalcao() {
        assertDoesNotThrow(() -> new MontadorClassico().montar(pedidoBalcao()));
    }

    @Test
    void deveExercutarMontadorGourmetBalcao() {
        assertDoesNotThrow(() -> new MontadorGourmet().montar(pedidoBalcao()));
    }

    @Test
    void deveExecutarMontadorClassicoDelivery() {
        assertDoesNotThrow(() -> new MontadorClassico().montar(pedidoDelivery()));
    }

    @Test
    void deveExercutarMontadorGourmetDelivery() {
        assertDoesNotThrow(() -> new MontadorGourmet().montar(pedidoDelivery()));
    }
}
