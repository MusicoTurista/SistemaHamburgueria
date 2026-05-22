package test;

import main.pagamento.AdapterMaquina;
import main.dominio.Dinheiro;
import main.pagamento.TerminalPagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdapterTest {
    @Test
    void deveRetornarTrueParaPagamentoAprovado() {
        TerminalPagamento terminal = new AdapterMaquina();
        assertTrue(terminal.processar("credito", new Dinheiro(50.0)));
    }

    @Test
    void deveAceitarPix() {
        TerminalPagamento terminal = new AdapterMaquina();
        assertDoesNotThrow(() -> terminal.processar("pix", new Dinheiro(30.0)));
    }

    @Test
    void deveAceitarDinheiro() {
        TerminalPagamento terminal = new AdapterMaquina();
        assertDoesNotThrow(() -> terminal.processar("dinheiro", new Dinheiro(20.0)));
    }

    @Test
    void deveImplementarTerminalPagamento() {
        assertInstanceOf(TerminalPagamento.class, new AdapterMaquina());
    }
}
