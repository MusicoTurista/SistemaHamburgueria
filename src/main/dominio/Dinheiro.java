package main.dominio;

public class Dinheiro {
    private int centavos;

    public Dinheiro(double reais) {
        this.centavos = (int) Math.round(reais * 100);
    }

    private Dinheiro(int c) {
        this.centavos = c;
    }

    public Dinheiro somar(Dinheiro o) {
        return new Dinheiro(centavos + o.centavos);
    }

    public Dinheiro multiplicar(double f) {
        return new Dinheiro((int) (centavos * f));
    }

    public double reais() {
        return centavos / 100.0;
    }

    @Override
    public String toString() {
        return String.format("R$ %.2f", reais());
    }
}