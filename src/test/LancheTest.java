package test;

import main.cardapio.Combo;
import main.cardapio.ItemAvulso;
import main.cardapio.LancheBase;
import main.dominio.ItemLanche;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class LancheTest {

    @Test
    void cloneLancheBaseDeveSerDiferente() {
        LancheBase original = new LancheBase("X-Burguer", 25.0);
        ItemLanche clone = original.clonar();

        assertNotSame(original, clone);
    }

    @Test
    void cloneLancheBaseDeveManterDescricao() {
        LancheBase original = new LancheBase("X-Burguer", 25.0);
        ItemLanche clone = original.clonar();

        assertEquals(original.descricao(), clone.descricao());
    }

    @Test
    void cloneLancheBaseDeveManterValor() {
        LancheBase original = new LancheBase("X-Burguer", 25.0);
        ItemLanche clone = original.clonar();

        assertEquals(original.preco().reais(), clone.preco().reais(), 0.001);
    }

    @Test
    void cloneItemAvulsoDeveSerDiferente() {
        ItemAvulso original = new ItemAvulso("Refrigerante", 6.0);
        ItemLanche clone = original.clonar();

        assertNotSame(original, clone);
    }

    @Test
    void cloneItemAvulsoDeveManterDescricao() {
        ItemAvulso original = new ItemAvulso("Refrigerante", 6.0);
        ItemLanche clone = original.clonar();

        assertEquals("Refrigerante", clone.descricao());
    }

    @Test
    void cloneComboDeveSerObjetoDiferente() {
        Combo combo = new Combo("Família", 0.10);
        combo.adicionar(new LancheBase("X-Burguer", 25.0));
        ItemLanche clone = combo.clonar();

        assertNotSame(combo, clone);
    }
}