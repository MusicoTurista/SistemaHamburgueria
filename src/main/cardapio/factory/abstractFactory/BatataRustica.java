package main.cardapio.factory.abstractFactory;

import main.dominio.Dinheiro;

public class BatataRustica implements Batata {
    public String descricao() {
        return "Batata Rústica Alecrim";
    }

    public Dinheiro preco() {
        return new Dinheiro(15.0);
    }
}