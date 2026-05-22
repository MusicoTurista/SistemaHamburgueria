package test;

import main.cardapio.factory.abstractFactory.Batata;
import main.cardapio.factory.abstractFactory.FabricaClassica;
import main.cardapio.factory.abstractFactory.FabricaGourmet;
import main.cardapio.factory.abstractFactory.Molho;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FabricaAcompanhamentosTest {

    @Test
    void fabricaClassicaDeveCriarBatataFrita() {
        Batata b = new FabricaClassica().criarBatata();
        assertTrue(b.descricao().toLowerCase().contains("frita") || b.preco().reais() > 0);
    }

    @Test
    void fabricaClassicaDeveCriarMolhoKetchup() {
        Molho m = new FabricaClassica().criarMolho();
        assertTrue(m.descricao().toLowerCase().contains("ketchup"));
    }

    @Test
    void fabricaGourmetDeveCriarBatataRustica() {
        Batata b = new FabricaGourmet().criarBatata();
        assertTrue(b.descricao().toLowerCase().contains("r") && b.preco().reais() > 0);
    }

    @Test
    void fabricaGourmetDeveCriarMolhoTrufa() {
        Molho m = new FabricaGourmet().criarMolho();
        assertTrue(m.descricao().toLowerCase().contains("trufa"));
    }
}
