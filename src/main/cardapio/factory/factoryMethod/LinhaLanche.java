package main.cardapio.factory.factoryMethod;

import main.cardapio.decorator.ComBacon;
import main.cardapio.decorator.ComQueijo;
import main.dominio.ItemLanche;

public abstract class LinhaLanche {
    public abstract ItemLanche criarLanche(String tipo);

    public ItemLanche pedirLanche(String tipo, boolean queijo, boolean bacon) {
        ItemLanche lanche = criarLanche(tipo);
        if (queijo) lanche = new ComQueijo(lanche);
        if (bacon) lanche = new ComBacon(lanche);
        return lanche;
    }
}
