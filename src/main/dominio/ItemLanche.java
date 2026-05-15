package main.dominio;

interface ItemLanche extends Cloneable {
    String   descricao();
    Dinheiro preco();
    ItemLanche clonar();
}