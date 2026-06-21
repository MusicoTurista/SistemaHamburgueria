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
        assertNotNull(Loja.getInstance().getNome());
        assertFalse(Loja.getInstance().getNome().isBlank());
    }

    @Test
    void taxaServicoDeveSerPositivoMenorQue1() {
        double taxa = Loja.getInstance().getTaxaServico();
        assertTrue(taxa > 0 && taxa < 1);
    }

    @Test
    void lojaDeveEstarAbertaPorPadrao() {
        assertTrue(Loja.getInstance().estaAberta());
    }

    @Test
    void limiteDoCardapioDeveSerPositivo() {
        assertTrue(Loja.getInstance().getLimiteCardapio() > 0);
    }
}
