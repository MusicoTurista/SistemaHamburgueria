package test;

import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import main.validacao.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacaoTest {
    private RegraValidacao montarCadeia() {
        RegraValidacao r1 = new RegraLojaAberta();
        RegraValidacao r2 = new RegraPedidoNaoVazio();
        RegraValidacao r3 = new RegraEnderecoDelivery();
        RegraValidacao r4 = new RegraValorMinimo();
        RegraValidacao r5 = new RegraEstoqueDisponivel();
        r1.encadear(r2).encadear(r3).encadear(r4).encadear(r5);
        return r1;
    }

    @Test
    void pedidoValidoDevePercorrerTodaACadeia() {
        Pedido p = new PedidoBuilder()
                .cliente("João")
                .item(new LancheBase("X-Burguer", 25.0))
                .build();
        assertTrue(montarCadeia().validar(p));
    }

    @Test
    void pedidoSemItensDeveSerRejeitado() {
        Pedido p = new PedidoBuilder()
                .cliente("João")
                .build();
        assertFalse(montarCadeia().validar(p));
    }

    @Test
    @DisplayName("Pedido delivery sem endereço é rejeitado")
    void deliverySemEnderecoDeveSerRejeitado() {
        RegraEnderecoDelivery regra = new RegraEnderecoDelivery();
        Pedido p = new PedidoBuilder()
                .cliente("X")
                .item(new LancheBase("L", 20.0))
                .delivery("")
                .build();
        assertFalse(regra.validar(p));
    }

    @Test
    void pedidoAbaixoDoValorMinimoDeveSerRejeitado() {
        Pedido p = new PedidoBuilder()
                .cliente("Y")
                .item(new ItemAvulso("Chiclete", 1.0))
                .build();
        assertFalse(montarCadeia().validar(p));
    }
}
