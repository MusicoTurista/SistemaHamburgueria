package main.cardapio;

import main.dominio.Dinheiro;
import main.dominio.ItemLanche;

public class ItemAvulso implements ItemLanche {
    private final String nome;
    private final Dinheiro preco;

    public ItemAvulso(String nome, double preco) {
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
            return (ItemAvulso) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
