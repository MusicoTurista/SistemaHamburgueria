package main.config;

public class Loja {
    private Loja() {}
    private static final Loja instance = new Loja();

    private final String nome = "Hamburgueria";
    private final double taxaServico = 0.10;
    private final int limiteCardapio = 50;
    private final double pedidoMinimo = 15;


    public static Loja getInstance() {
        return instance;
    }

    public boolean estaAberta() {
        return true;
    }

    public String getNome() {
        return nome;
    }

    public double getTaxaServico() {
        return taxaServico;
    }

    public int getLimiteCardapio() {
        return limiteCardapio;
    }

    public double getPedidoMinimo() {
        return pedidoMinimo;
    }
}

