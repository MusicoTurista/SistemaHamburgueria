package main.dominio;

public interface ItemLanche extends Cloneable {
    String descricao();

    Dinheiro preco();

    ItemLanche clonar();
}