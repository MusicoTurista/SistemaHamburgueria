package main.config;

public class Loja {
    private Loja() {}
    private static Loja instance = new Loja();

    public final String nome = "Hamburgueria";
    public final double taxaServico = 0.10;
    public final int limiteCardapio = 50;


    public static Loja getInstance() {
        return instance;
    }

    public boolean estaAberta() {
        return true;
    }
}

