package test;

import main.cardapio.Cardapio;
import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import main.config.Loja;
import main.dominio.ItemLanche;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardapioTest {
    @Test
    void deveIterarItensAdicionados() {
        Cardapio c = new Cardapio();
        c.adicionar(new LancheBase("A", 10.0));
        c.adicionar(new LancheBase("B", 20.0));

        List<String> nomes = new ArrayList<>();
        for (ItemLanche i : c) nomes.add(i.descricao());

        assertTrue(nomes.contains("A"));
        assertTrue(nomes.contains("B"));
    }

    @Test
    void cardapioVazioNaoDeveIterar() {
        Cardapio c = new Cardapio();
        assertDoesNotThrow(() -> {
            for (ItemLanche i : c) {
            }
        });
    }

    @Test
    void deveRespeitarLimiteDaLoja() {
        Cardapio c = new Cardapio();
        int limite = Loja.getInstance().limiteCardapio;
        for (int i = 0; i <= limite + 5; i++)
            c.adicionar(new ItemAvulso("Item" + i, 1.0));

        long count = 0;
        for (ItemLanche i : c) count++;
        assertTrue(count <= limite);
    }

    @Test
    void devePercorrerTodosOsItens() {
        Cardapio c = new Cardapio();
        for (int i = 0; i < 5; i++) c.adicionar(new ItemAvulso("I" + i, i + 1.0));

        int count = 0;
        for (ItemLanche i : c) count++;
        assertEquals(5, count);
    }
}
