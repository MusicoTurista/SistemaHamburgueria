package test;

import main.caixa.SessaoCaixa;
import main.caixa.SessaoCaixaSnapshot;
import main.cardapio.LancheBase;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessaoCaixaTest {
    private Pedido pedido(double valor) {
        return new PedidoBuilder()
                .cliente("T")
                .item(new LancheBase("X", valor))
                .build();
    }

    @Test
    void deveSalvarEstadoDaSessao() {
        SessaoCaixa sessao = new SessaoCaixa();
        sessao.registrar(pedido(50.0));
        SessaoCaixaSnapshot snap = sessao.salvar();

        assertNotNull(snap);
        assertEquals(55.0, snap.getTotal().reais(), 0.01);
    }

    @Test
    void restaurar() {
        SessaoCaixa sessao = new SessaoCaixa();
        SessaoCaixaSnapshot snapVazio = sessao.salvar();

        sessao.registrar(pedido(100.0));
        assertEquals(1, sessao.salvar().getNumPed());

        sessao.restaurar(snapVazio);
        assertEquals(0, sessao.salvar().getNumPed());
    }

    @Test
    void snapshotDeveSerImutavel() {
        SessaoCaixa sessao = new SessaoCaixa();
        SessaoCaixaSnapshot snap = sessao.salvar();

        sessao.registrar(pedido(200.0));

        assertEquals(0, snap.getNumPed());
    }

    @Test
    void deveAcumularTotalCorretamente() {
        SessaoCaixa sessao = new SessaoCaixa();
        sessao.registrar(pedido(100.0));
        sessao.registrar(pedido(50.0));

        SessaoCaixaSnapshot snap = sessao.salvar();
        assertEquals(2, snap.getNumPed());
        assertTrue(snap.getTotal().reais() > 150.0);
    }
}
