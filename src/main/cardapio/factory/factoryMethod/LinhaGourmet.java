package main.cardapio.factory.factoryMethod;

import main.cardapio.LancheBase;
import main.dominio.ItemLanche;

public class LinhaGourmet extends LinhaLanche {
    @Override
    public ItemLanche criarLanche(String tipo) {
        return switch (tipo) {
            case "wagyu" -> new LancheBase("Wagyu Smash", 58.0);
            default -> new LancheBase("Black Angus 180g", 45.0);
        };
    }
}