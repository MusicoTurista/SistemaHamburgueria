package test;

import main.cardapio.LancheBase;
import main.dominio.Dinheiro;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import main.frete.EstrategiaFrete;
import main.frete.FreteGratis;
import main.frete.FretePorDistancia;
import main.frete.ServicoDelivery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicoDeliveryTest {
    private Pedido balcao() {
        return new PedidoBuilder().cliente("T").item(new LancheBase("X", 30.0)).build();
    }

    private Pedido delivery(String end) {
        return new PedidoBuilder().cliente("T").item(new LancheBase("X", 30.0))
                .delivery(end).build();
    }

    @Test
    void balcaoDeveTerFreteGratis() {
        ServicoDelivery sd = new ServicoDelivery(balcao(), 0.0);
        assertEquals(0.0, sd.calcular(0.0, new Dinheiro(30.0)).reais(), 0.001);
    }

    @Test
    void deveUsarFreteFixoAte2Km() {
        ServicoDelivery sd = new ServicoDelivery(delivery("Rua A, 1"), 2);
        assertEquals(5.0, sd.calcular(1.5, new Dinheiro(30.0)).reais(), 0.001);
    }

    @Test
    void deveUsarFretePorDistanciaAcimaDe2Km() {
        ServicoDelivery sd = new ServicoDelivery(delivery("Rua B, 99"), 2.1);
        assertEquals(6, sd.calcular(2, new Dinheiro(30.0)).reais(), 0.001);
    }

    @Test
    void freteGratisSempreRetorna0() {
        EstrategiaFrete eg = new FreteGratis();
        assertEquals(0.0, eg.calcular(100.0, new Dinheiro(999.0)).reais(), 0.001);
    }

    @Test
    void fretePorDistanciaDeveAumentarProporcionalmente() {
        EstrategiaFrete fk = new FretePorDistancia();
        double f3 = fk.calcular(3.0, new Dinheiro(0)).reais();
        double f6 = fk.calcular(6.0, new Dinheiro(0)).reais();
        assertTrue(f6 > f3);
    }
}
