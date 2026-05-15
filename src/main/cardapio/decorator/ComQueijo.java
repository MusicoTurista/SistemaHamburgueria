package main.cardapio.decorator;

import main.dominio.ItemLanche;

public class ComQueijo extends Adicional {
    public ComQueijo(ItemLanche item) {
        super(item, "Queijo Cheddar", 3.0);
    }
}
