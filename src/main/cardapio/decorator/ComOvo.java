package main.cardapio.decorator;

import main.dominio.ItemLanche;

public class ComOvo extends Adicional {
    public ComOvo(ItemLanche item) {
        super(item, "Ovo Frito", 2.5);
    }
}
