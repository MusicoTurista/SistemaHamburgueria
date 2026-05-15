package main.cardapio;

import main.config.Loja;
import main.dominio.ItemLanche;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cardapio implements Iterable<ItemLanche> {
    private final List<ItemLanche> itens = new ArrayList<>();

    public void adicionar(ItemLanche i) {
        if (itens.size() < Loja.getInstance().limiteCardapio) itens.add(i);
    }

    public void exibir() {
        System.out.println("\n  === CARDÁPIO — " + Loja.getInstance().nome + " ===");
        for (ItemLanche i : this)
            System.out.printf("  %-42s %s%n", i.descricao(), i.preco());
    }

    @Override
    public Iterator<ItemLanche> iterator() {
        return itens.iterator();
    }
}
