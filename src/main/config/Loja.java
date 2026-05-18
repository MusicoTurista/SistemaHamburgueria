package main.config;

public class Loja {
    private static Loja instance = new Loja();
    public final String nome = "Hamburgueria";
    public final double taxaServico = 0.10;
    public final int limiteCardapio = 50;

    private Loja() {
    }

    public static Loja getInstance() {
        return instance;
    }

    public boolean estaAberta() {
        return true;
    }
}

