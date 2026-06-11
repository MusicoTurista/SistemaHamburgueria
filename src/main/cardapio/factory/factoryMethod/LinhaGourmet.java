package main.cardapio.factory.factoryMethod;

import main.cardapio.LancheBase;
import main.cardapio.factory.abstractFactory.FabricaAcompanhamentos;
import main.cardapio.factory.abstractFactory.FabricaGourmet;
import main.cozinha.MontadorGourmet;
import main.cozinha.ProcessoMontagem;
import main.dominio.ItemLanche;

public class LinhaGourmet extends LinhaLanche {
    @Override
    public FabricaAcompanhamentos getFabricaAcompanhamentos() {
        return new FabricaGourmet();
    }

    @Override
    public ProcessoMontagem criarMontador() {
        return new MontadorGourmet();
    }

    @Override
    public ItemLanche criarLanche(String tipo) {
        return switch (tipo) {
            case "wagyu" -> new LancheBase("Wagyu Smash", 58.0);
            default -> new LancheBase("Black Angus 180g", 45.0);
        };
    }
}