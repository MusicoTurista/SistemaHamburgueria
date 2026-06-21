package test;

import main.cardapio.LancheBase;
import main.dominio.Dinheiro;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import main.promocao.ExpressaoDesconto;
import main.promocao.InterpretadorPromocao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromocaoTest {
    private Pedido pedidoBalcao() {
        return new PedidoBuilder()
                .cliente("X")
                .item(new LancheBase("L", 100.0))
                .build();
    }

    private Pedido pedidoDelivery() {
        return new PedidoBuilder().cliente("X")
                .item(new LancheBase("L", 100.0))
                .delivery("Rua A, 1")
                .build();
    }

    @Test
    void deveRetornarSemPromocao() {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar(null);
        assertEquals(100.0, e.aplicar(new Dinheiro(100.0), pedidoBalcao()).reais(), 0.001);
    }

    @Test
    void deveAplicar10PorcentoDeDesconto() {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar("DESCONTO_10PCT");
        assertEquals(90.0, e.aplicar(new Dinheiro(100.0), pedidoBalcao()).reais(), 0.001);
    }

    @Test
    void deveAplicar15PorcentoDeDesconto() {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar("DESCONTO_15PCT");
        assertEquals(85.0, e.aplicar(new Dinheiro(100.0), pedidoBalcao()).reais(), 0.001);
    }

    @Test
    void deveAplicar10PorcentoDeDescontoEmDelivery() {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar("DELIVERY_DESCONTO_10PCT");
        assertEquals(90.0, e.aplicar(new Dinheiro(100.0), pedidoDelivery()).reais(), 0.001);
    }
    @Test
    void naoDeveAplicar10PorcentoDeDescontoSemDelivery() {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar("DELIVERY_DESCONTO_10PCT");
        assertEquals(100.0, e.aplicar(new Dinheiro(100.0), pedidoBalcao()).reais(), 0.001);
    }

    @Test
    void regraInvalidaDeveRetornarSemDesconto() {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar("PROMO_INVALIDA");
        assertEquals(50.0, e.aplicar(new Dinheiro(50.0), pedidoBalcao()).reais(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({"10, 90.0", "20, 80.0", "50, 50.0"})
    void deveAplicarDescontosParametrizados(int pct, double esperado) {
        ExpressaoDesconto e = InterpretadorPromocao.interpretar("DESCONTO_" + pct + "PCT");
        assertEquals(esperado, e.aplicar(new Dinheiro(100.0), pedidoBalcao()).reais(), 0.001);
    }
}
