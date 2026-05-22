package test;

import main.cardapio.factory.factoryMethod.LinhaClassica;
import main.dominio.ItemLanche;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinhaLancheTest {
    @Test
    void deveCriarLinhaClassicaSimples() {
        ItemLanche l = new LinhaClassica().criarLanche("simples");
        assertTrue(l.descricao().toLowerCase().contains("simples") || l.preco().reais() > 0);
    }

    @Test
    void deveCriarLinhaClassicaDuplo() {
        ItemLanche l = new LinhaClassica().criarLanche("duplo");
        assertTrue(l.descricao().toLowerCase().contains("duplo") || l.preco().reais() > 0);
    }

    @Test
    void pedirComQueijoDeveAplicarDecorator() {
        ItemLanche l = new LinhaClassica().pedirLanche("simples", true, false);
        assertTrue(l.descricao().contains("Queijo"));
    }

    @Test
    void pedirComBaconDeveAplicarDecorator() {
        ItemLanche l = new LinhaClassica().pedirLanche("simples", false, true);
        assertTrue(l.descricao().contains("Bacon"));
    }

    @Test
    void pedirSemAdicionaisRetornaLancheBase() {
        ItemLanche base = new LinhaClassica().criarLanche("simples");
        ItemLanche pedido = new LinhaClassica().pedirLanche("simples", false, false);
        assertEquals(base.preco().reais(), pedido.preco().reais(), 0.001);
    }
}
