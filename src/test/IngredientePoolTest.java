package test;

import main.cardapio.flyweight.Ingrediente;
import main.cardapio.flyweight.IngredientePool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientePoolTest {
    @Test
    void mesmaChaveDeveRetornarMesmoObjeto() {
        Ingrediente a = IngredientePool.get("Alface", "Horta A", 15);
        Ingrediente b = IngredientePool.get("Alface", "Horta A", 15);
        assertSame(a, b);
    }

    @Test
    void chavesDiferentesDevemGerarObjetosDiferentes() {
        Ingrediente alface = IngredientePool.get("Alface2", "Horta A", 15);
        Ingrediente tomate = IngredientePool.get("Tomate2", "Horta B", 18);
        assertNotSame(alface, tomate);
    }

    @Test
    void usarDeveRetornarNomeEPeso() {
        Ingrediente ing = IngredientePool.get("Cebola", "Horta C", 40);
        String resultado = ing.usar(50);
        assertTrue(resultado.contains("Cebola"));
        assertTrue(resultado.contains("50"));
    }

    @Test
    void poolNaoDeveDuplicarObjetos() {
        IngredientePool.get("IngTeste", "F", 10);
        int antes = IngredientePool.tamanho();
        IngredientePool.get("IngTeste", "F", 10);
        int depois = IngredientePool.tamanho();
        assertEquals(antes, depois);
    }
}
