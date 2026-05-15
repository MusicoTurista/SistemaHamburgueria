package main.cardapio.factory.abstractFactory;

public class FabricaClassica implements FabricaAcompanhamentos {
    public Batata criarBatata() {
        return new BatataFrita();
    }

    public Molho criarMolho() {
        return new MolhoKetchup();
    }
}
