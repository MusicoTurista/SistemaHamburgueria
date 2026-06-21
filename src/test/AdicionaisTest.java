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
    void comQueijoDeveAcumularDescricao() {
        ItemLanche c = new ComQueijo(base);
        assertTrue(c.descricao().contains("Queijo"));
    }
    @Test
    void comQueijoDeveAcumularPreco() {
        ItemLanche c = new ComQueijo(base);
        assertEquals(21.0, c.preco().reais(), 0.001);
    }

    @Test
    void comBaconDeveAcumularDescricao() {
        ItemLanche c = new ComBacon(base);
        assertTrue(c.descricao().contains("Bacon"));
    }
    @Test
    void comBaconDeveAcumularPreco() {
        ItemLanche c = new ComBacon(base);
        assertEquals(22.0, c.preco().reais(), 0.001);
    }

    @Test
    void comOvoDeveAcumularDescricao() {
        ItemLanche c = new ComOvo(base);
        assertTrue(c.descricao().contains("Ovo"));
    }
    @Test
    void comOvoDeveAcumularPreco() {
        ItemLanche c = new ComOvo(base);
        assertEquals(20.5, c.preco().reais(), 0.001);
    }

    @Test
    void itemLancheDeveRetornarPrecoAcumulado() {
        ItemLanche resultado = new ComOvo(new ComBacon(new ComQueijo(base)));
        assertEquals(27.5, resultado.preco().reais(), 0.001);
    }

    @Test
    void descricaoDeveConterTodosAdicionais() {
        ItemLanche resultado = new ComOvo(new ComBacon(new ComQueijo(base)));
        String desc = resultado.descricao();
        assertTrue(desc.contains("Queijo") & desc.contains("Bacon") & desc.contains("Ovo"));
    }
}
