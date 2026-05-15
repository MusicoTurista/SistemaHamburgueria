package main.cardapio;

import main.dominio.Dinheiro;
import main.dominio.ItemLanche;

public class LancheBase implements ItemLanche {
    private String nome;
    private Dinheiro preco;

    public LancheBase(String nome, double preco) {
        this.nome = nome;
        this.preco = new Dinheiro(preco);
    }

    @Override
    public String descricao() {
        return nome;
    }

    @Override
    public Dinheiro preco() {
        return preco;
    }

    @Override
    public ItemLanche clonar() {
        try {
            return (LancheBase) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

