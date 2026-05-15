package main.config;

public class Loja {
    private static Loja instance;
    public final String nome = "Hamburgueria";
    public final double taxaServico = 0.10;
    public final int limiteCardapio = 50;

    private Loja() {
    }

    public static Loja getInstance() {
        if (instance == null) instance = new Loja();
        return instance;
    }

    public boolean estaAberta() {
        return true;
    }
}

