package main.cardapio.factory.abstractFactory;

public class FabricaGourmet implements FabricaAcompanhamentos {
    public Batata criarBatata() {
        return new BatataRustica();
    }

    public Molho criarMolho() {
        return new MolhoTrufa();
    }
}