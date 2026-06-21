package test;

import main.cardapio.Combo;
import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComboTest {
    @Test
    void deveRetornarPrecoDoComboComDesconto() {
        Combo combo = new Combo("Duplo", 0.10);
        combo.adicionar(new LancheBase("Burguer", 30.0));
        combo.adicionar(new ItemAvulso("Batata",  10.0));
        combo.adicionar(new ItemAvulso("Refri",    6.0));
        assertEquals(41.4, combo.preco().reais(), 0.001);
    }

    @Test
    void deveRetornarPrecoDoComboSemDesconto() {
        Combo combo = new Combo("Simples", 0.0);
        combo.adicionar(new ItemAvulso("Item A", 10.0));
        combo.adicionar(new ItemAvulso("Item B", 20.0));
        assertEquals(30.0, combo.preco().reais(), 0.001);
    }

    @Test
    void descricaoDeveIncluirNomeDoComboEItens() {
        Combo combo = new Combo("Especial", 0.0);
        combo.adicionar(new LancheBase("X-Burguer", 25.0));
        assertTrue(combo.descricao().contains("X-Burguer") & combo.descricao().contains("Especial"));
    }

    @Test
    void comboVazioDeveTerPreco0() {
        Combo combo = new Combo("Vazio", 0.10);
        assertEquals(0.0, combo.preco().reais(), 0.001);
    }
}
