package main.cardapio.factory.abstractFactory;

import main.dominio.Dinheiro;

public class BatataFrita implements Batata {
    public String descricao() {
        return "Batata Frita";
    }

    public Dinheiro preco() {
        return new Dinheiro(9.0);
    }
}