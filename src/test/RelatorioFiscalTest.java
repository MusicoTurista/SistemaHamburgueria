package test;

import main.caixa.ItemPedidoVisitavel;
import main.caixa.RelatorioFiscal;
import main.caixa.VisitorFiscal;
import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import main.dominio.ItemLanche;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelatorioFiscalTest {
    @Test
    void deveVisitarLanche() {
        RelatorioFiscal r = new RelatorioFiscal();
        new ItemPedidoVisitavel(new LancheBase("X", 30.0), "lanche").aceitar(r);
        new ItemPedidoVisitavel(new LancheBase("Y", 20.0), "lanche").aceitar(r);
        assertDoesNotThrow(r::imprimirResumo);
    }

    @Test
    void deveVisitarBebida() {
        RelatorioFiscal r = new RelatorioFiscal();
        new ItemPedidoVisitavel(new ItemAvulso("Refri", 6.0), "bebida").aceitar(r);
        assertDoesNotThrow(r::imprimirResumo);
    }

    @Test
    void itemPedidoVisitavelDeveDelegarVisitor() {
        List<String> log = new ArrayList<>();
        VisitorFiscal spy = new VisitorFiscal() {
            public void visitar(ItemPedidoVisitavel i) {
                log.add("visitado:" + i.getCategoria());
            }

            public void imprimirResumo() {
            }
        };

        new ItemPedidoVisitavel(new LancheBase("X", 10.0), "lanche").aceitar(spy);
        new ItemPedidoVisitavel(new ItemAvulso("B", 5.0), "bebida").aceitar(spy);

        assertEquals(List.of("visitado:lanche", "visitado:bebida"), log);
    }

    @Test
    void getLancheDeveRetornarItemOriginal() {
        ItemLanche original = new LancheBase("X-Burguer", 25.0);
        ItemPedidoVisitavel visitavel = new ItemPedidoVisitavel(original, "lanche");
        assertSame(original, visitavel.getLanche());
    }
}
