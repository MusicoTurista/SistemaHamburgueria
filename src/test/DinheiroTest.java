package test;

import main.dominio.Dinheiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DinheiroTest {

    @Test
    void deveCriarValorAPartirDeReais() {
        Dinheiro d = new Dinheiro(10.99);
        assertEquals(10.99, d.reais(), 0.001);
    }

    @Test
    void deveSomarDinheiro() {
        Dinheiro a = new Dinheiro(10.0);
        Dinheiro b = new Dinheiro(5.50);
        assertEquals(15.50, a.somar(b).reais(), 0.001);
    }

    @Test
    void deveMultiplicarDinheiro() {
        Dinheiro d = new Dinheiro(100.0);
        assertEquals(90.0, d.multiplicar(0.9).reais(), 0.001);
    }

    @Test
    void deveSomarDinheiroEncadeado() {
        Dinheiro total = new Dinheiro(0);
        total = total.somar(new Dinheiro(18.0))
            .somar(new Dinheiro(6.0))
            .somar(new Dinheiro(9.0));
        assertEquals(33.0, total.reais(), 0.001);
    }

    @Test
    void deveFormatarValor() {
        assertEquals("R$ 25,00", new Dinheiro(25.0).toString());
    }
}