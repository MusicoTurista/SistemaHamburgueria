package main.cardapio.decorator;

import main.dominio.Dinheiro;
import main.dominio.ItemLanche;

public abstract class Adicional implements ItemLanche {
    protected final ItemLanche base;
    private final String nomeAdd;
    private final Dinheiro precoAdd;

    protected Adicional(ItemLanche base, String nome, double preco) {
        this.base = base;
        this.nomeAdd = nome;
        this.precoAdd = new Dinheiro(preco);
    }

    @Override
    public String descricao() {
        return base.descricao() + " + " + nomeAdd;
    }

    @Override
    public Dinheiro preco() {
        return base.preco().somar(precoAdd);
    }

    @Override
    public ItemLanche clonar() {
        try {
            return (Adicional) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
