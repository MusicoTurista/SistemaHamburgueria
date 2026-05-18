package test;

import main.config.Loja;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LojaTest {

    @Test
    void lojaDeveSerSingleton() {
        Loja a = Loja.getInstance();
        Loja b = Loja.getInstance();
        assertSame(a, b);
    }

    @Test
    void lojaDevePossuirNome() {
        assertNotNull(Loja.getInstance().nome);
        assertFalse(Loja.getInstance().nome.isBlank());
    }

    @Test
    void taxaServicoDeveSerPositivoMenorQue1() {
        double taxa = Loja.getInstance().taxaServico;
        assertTrue(taxa > 0 && taxa < 1);
    }

    @Test
    void lojaDeveEstarAbertaPorPadrao() {
        assertTrue(Loja.getInstance().estaAberta());
    }

    @Test
    void limiteDoCardapioDeveSerPositivo() {
        assertTrue(Loja.getInstance().limiteCardapio > 0);
    }
}
