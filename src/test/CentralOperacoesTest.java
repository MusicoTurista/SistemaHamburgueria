package test;

import main.cardapio.LancheBase;
import main.cozinha.*;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CentralOperacoesTest {
    @Test
    void mensagemPublicadaDeveSerEntregue() {
        CentralOperacoes central = new CentralOperacoes();
        List<String> recebidas = new ArrayList<>();

        Setor s1 = new Setor() {
            public String nome() {
                return "S1";
            }

            public void receberMensagem(String r, String ev, Object d) {
                recebidas.add("S1:" + ev);
            }
        };
        Setor s2 = new Setor() {
            public String nome() {
                return "S2";
            }

            public void receberMensagem(String r, String ev, Object d) {
                recebidas.add("S2:" + ev);
            }
        };

        central.registrar(s1);
        central.registrar(s2);
        central.publicar("S1", "TESTE", null);

        assertTrue(recebidas.contains("S2:TESTE"));
    }

    @Test
    void mensagemPublicadaDeveSerEntreguecomExessaoDoRemetente() {
        CentralOperacoes central = new CentralOperacoes();
        List<String> recebidas = new ArrayList<>();

        Setor s1 = new Setor() {
            public String nome() {
                return "S1";
            }

            public void receberMensagem(String r, String ev, Object d) {
                recebidas.add("S1:" + ev);
            }
        };
        Setor s2 = new Setor() {
            public String nome() {
                return "S2";
            }

            public void receberMensagem(String r, String ev, Object d) {
                recebidas.add("S2:" + ev);
            }
        };

        central.registrar(s1);
        central.registrar(s2);
        central.publicar("S1", "TESTE", null);

        assertFalse(recebidas.contains("S1:TESTE"));
    }

    @Test
    void cozinhaReagePedidoPago() {
        CentralOperacoes central = new CentralOperacoes();
        FilaCozinha fila = new FilaCozinha();
        SetorCozinha cozinha = new SetorCozinha(central, fila);
        central.registrar(cozinha);

        Pedido p = new PedidoBuilder()
                .cliente("T")
                .item(new LancheBase("X", 20.0))
                .build();

        assertDoesNotThrow(() -> cozinha.receberMensagem("CAIXA", "PEDIDO_PAGO", p));
    }

    @Test
    void entregaReagePedidoPronto() {
        CentralOperacoes central = new CentralOperacoes();
        SetorEntrega entrega = new SetorEntrega(central);
        Pedido p = new PedidoBuilder()
                .cliente("T")
                .item(new LancheBase("X", 20.0))
                .build();

        assertDoesNotThrow(() -> entrega.receberMensagem("COZINHA", "PEDIDO_PRONTO", p));
    }
}
