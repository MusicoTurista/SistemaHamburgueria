package test;

import main.cardapio.LancheBase;
import main.cozinha.ComandoCozinha;
import main.cozinha.ComandoMontar;
import main.cozinha.FilaCozinha;
import main.cozinha.MontadorClassico;
import main.dominio.Pedido;
import main.dominio.PedidoBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilaCozinhaTest {
    private Pedido pedidoValido() {
        return new PedidoBuilder()
                .cliente("Test")
                .item(new LancheBase("X", 20.0))
                .build();
    }

    @Test
    void filaCozinhaDeveExecutarEGerarHistorico() {
        FilaCozinha fila = new FilaCozinha();
        List<String> log = new ArrayList<>();
        ComandoCozinha cmd = new ComandoCozinha() {
            public void executar() {
                log.add("executado");
            }

            public void desfazer() {
                log.add("desfeito");
            }

            public String descricao() {
                return "teste";
            }
        };
        fila.executar(cmd);
        assertEquals("executado", log.getFirst());
    }

    @Test
    void deveDesfazerUltimoComando() {
        FilaCozinha fila = new FilaCozinha();
        List<String> log = new ArrayList<>();
        ComandoCozinha cmd = new ComandoCozinha() {
            public void executar() {
                log.add("executado");
            }

            public void desfazer() {
                log.add("desfeito");
            }

            public String descricao() {
                return "teste";
            }
        };
        fila.executar(cmd);
        fila.desfazerUltimo();
        assertTrue(log.contains("desfeito"));
    }

    @Test
    void deveExecutarMontagem() {
        Pedido p = pedidoValido();
        assertDoesNotThrow(() -> {
            ComandoMontar cmd = new ComandoMontar(new MontadorClassico(), p);
            cmd.executar();
        });
    }

    @Test
    void comandosDevemSerDesfeitosEmOrdemLIFO() {
        FilaCozinha fila = new FilaCozinha();
        List<String> log = new ArrayList<>();

        fila.executar(new ComandoCozinha() {
            public void executar() {
                log.add("exec1");
            }

            public void desfazer() {
                log.add("undo1");
            }

            public String descricao() {
                return "1";
            }
        });
        fila.executar(new ComandoCozinha() {
            public void executar() {
                log.add("exec2");
            }

            public void desfazer() {
                log.add("undo2");
            }

            public String descricao() {
                return "2";
            }
        });

        fila.desfazerUltimo();
        assertEquals("undo2", log.getLast());
    }
}
