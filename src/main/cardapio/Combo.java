package main.cardapio;

import main.dominio.Dinheiro;
import main.dominio.ItemLanche;

import java.util.ArrayList;
import java.util.List;

public class Combo implements ItemLanche {
    private final String nome;
    private final List<ItemLanche> itens = new ArrayList<>();
    private final double desconto;

    public Combo(String nome, double desconto) {
        this.nome = nome;
        this.desconto = desconto;
    }

    public void adicionar(ItemLanche item) {
        itens.add(item);
    }

    @Override
    public String descricao() {
        StringBuilder sb = new StringBuilder("[Combo " + nome + ": ");
        for (int i = 0; i < itens.size(); i++) {
            sb.append(itens.get(i).descricao());
            if (i < itens.size() - 1) sb.append(" | ");
        }
        return sb.append("]").toString();
    }

    @Override
    public Dinheiro preco() {
        Dinheiro t = new Dinheiro(0);
        for (ItemLanche i : itens) t = t.somar(i.preco());
        return t.multiplicar(1 - desconto);
    }

    @Override
    public ItemLanche clonar() {
        try {
            return (Combo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}