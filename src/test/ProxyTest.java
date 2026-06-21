package test;

import main.dominio.Dinheiro;
import main.pagamento.ProxyTerminal;
import org.junit.jupiter.api.Test;
import test.helper.TerminalAprovado;

import static org.junit.jupiter.api.Assertions.*;

class ProxyTest {

    @Test
    void deveDelegarQuandoOnline() {
        TerminalAprovado real = new TerminalAprovado();
        ProxyTerminal proxy = new ProxyTerminal(real);

        assertTrue(proxy.processar("pix", new Dinheiro(50.0)));
    }
    @Test
    void deveGuardarChamadasQuandoOnline() {
        TerminalAprovado real = new TerminalAprovado();
        ProxyTerminal proxy = new ProxyTerminal(real);
        proxy.processar("pix", new Dinheiro(50.0));

        assertEquals(1, real.getChamadas());
    }

    @Test
    void deveBloquearQuandoOffline() {
        TerminalAprovado real = new TerminalAprovado();
        ProxyTerminal proxy = new ProxyTerminal(real);
        proxy.setOnline(false);

        assertFalse(proxy.processar("pix", new Dinheiro(50.0)));
    }
    @Test
    void naoDeveGuardarChamadasQuandoOffline() {
        TerminalAprovado real = new TerminalAprovado();
        ProxyTerminal proxy = new ProxyTerminal(real);
        proxy.setOnline(false);

        assertEquals(0, real.getChamadas());
    }

    @Test
    void deveLiberaTerminalAposTransacao() {
        TerminalAprovado real = new TerminalAprovado();
        ProxyTerminal proxy = new ProxyTerminal(real);

        proxy.processar("pix", new Dinheiro(10.0));
        assertTrue(proxy.processar("pix", new Dinheiro(10.0)));
    }
}
