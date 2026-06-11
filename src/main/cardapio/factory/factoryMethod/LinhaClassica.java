package main.cardapio.factory.factoryMethod;

import main.cardapio.LancheBase;
import main.cardapio.factory.abstractFactory.FabricaAcompanhamentos;
import main.cardapio.factory.abstractFactory.FabricaClassica;
import main.dominio.ItemLanche;

public class LinhaClassica extends LinhaLanche {
    @Override
    public FabricaAcompanhamentos getFabricaAcompanhamentos() { return new FabricaClassica(); }
    @Override
    public ItemLanche criarLanche(String tipo) {
        return switch (tipo) {
            case "duplo" -> new LancheBase("X-Duplo", 26.0);
            default -> new LancheBase("X-Simples", 18.0);
        };
    }
}
