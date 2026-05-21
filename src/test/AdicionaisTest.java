package test;

import main.cardapio.LancheBase;
import main.cardapio.decorator.ComBacon;
import main.cardapio.decorator.ComOvo;
import main.cardapio.decorator.ComQueijo;
import main.dominio.ItemLanche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdicionaisTest {
    private ItemLanche base;

    @BeforeEach
    void setup() {
        base = new LancheBase("X-Simples", 18.0);
    }

    @Test
    void comQueijoDeveAcumularDescricaoEPreco() {
        ItemLanche c = new ComQueijo(base);
        assertTrue(c.descricao().contains("Queijo"));
        assertEquals(21.0, c.preco().reais(), 0.001); // 18 + 3
    }

    @Test
    void comBaconDeveAcumularDescricaoEPreco() {
        ItemLanche c = new ComBacon(base);
        assertTrue(c.descricao().contains("Bacon"));
        assertEquals(22.0, c.preco().reais(), 0.001); // 18 + 4
    }

    @Test
    void comOvoDeveAcumularDescricaoEPreco() {
        ItemLanche c = new ComOvo(base);
        assertTrue(c.descricao().contains("Ovo"));
        assertEquals(20.5, c.preco().reais(), 0.001); // 18 + 2.5
    }

    @Test
    void itemLancheDeveRetornarPrecoAcumulado() {
        ItemLanche resultado = new ComOvo(new ComBacon(new ComQueijo(base)));
        // 18 + 3 + 4 + 2.5 = 27.5
        assertEquals(27.5, resultado.preco().reais(), 0.001);
    }

    @Test
    void descricaoDeveConterTodosAdicionais() {
        ItemLanche resultado = new ComOvo(new ComBacon(new ComQueijo(base)));
        String desc = resultado.descricao();
        assertTrue(desc.contains("Queijo"));
        assertTrue(desc.contains("Bacon"));
        assertTrue(desc.contains("Ovo"));
    }
}
