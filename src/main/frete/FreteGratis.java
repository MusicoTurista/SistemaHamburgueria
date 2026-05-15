package main.frete;

import main.dominio.Dinheiro;

public class FreteGratis implements EstrategiaFrete {
    public Dinheiro calcular(double d, Dinheiro v) {
        return new Dinheiro(0);
    }

    public String modalidade() {
        return "Retirada no balcão (grátis)";
    }
}
