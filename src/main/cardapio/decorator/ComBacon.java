package main.cardapio.decorator;

import main.dominio.ItemLanche;

public class ComBacon extends Adicional {
    public ComBacon(ItemLanche item) {
        super(item, "Bacon Crocante", 4.0);
    }
}