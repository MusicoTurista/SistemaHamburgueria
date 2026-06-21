package test;

import main.cardapio.Cardapio;
import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import main.config.Loja;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardapioTest {
    @Test
    void deveIterarItensAdicionados() {
        Cardapio c = new Cardapio();
        c.adicionar(new LancheBase("A", 10.0));
        c.adicionar(new LancheBase("B", 20.0));
        List<String> nomes = c.getNomes();
        assertTrue(nomes.contains("A") & nomes.contains("B"));
        System.out.println(c.iterator());
    }

    @Test
    void deveRespeitarLimiteDaLoja() {
        Cardapio c = new Cardapio();
        int limite = Loja.getInstance().getLimiteCardapio();
        for (int i = 0; i <= limite + 5; i++)
            c.adicionar(new ItemAvulso("Item" + i, 1.0));

        assertEquals(c.tamanho(), limite);
    }

    @Test
    void devePercorrerTodosOsItens() {
        Cardapio c = new Cardapio();
        for (int i = 0; i < 5; i++) c.adicionar(new ItemAvulso("I" + i, i + 1.0));

        assertEquals(5, c.tamanho());
    }
}
