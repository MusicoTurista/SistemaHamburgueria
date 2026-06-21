package main.cardapio;

import main.config.Loja;
import main.dominio.ItemLanche;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cardapio implements Iterable<ItemLanche> {
    private final List<ItemLanche> itens = new ArrayList<>();

    public int tamanho() {
        return itens.size();
    }

    public void adicionar(ItemLanche i) {
        if (itens.size() < Loja.getInstance().getLimiteCardapio()) itens.add(i);
    }

    public void exibir() {
        System.out.println("\n  === CARDÁPIO — " + Loja.getInstance().getNome() + " ===");
        for (ItemLanche i : this)
            System.out.printf("  %-42s %s%n", i.descricao(), i.preco());
    }

    public List<String> getNomes() {
        List<String> nomes = new ArrayList<>();
        for (ItemLanche i : this.itens) nomes.add(i.descricao());
        return nomes;
    }

    @Override
    public Iterator<ItemLanche> iterator() {
        return itens.iterator();
    }
}
