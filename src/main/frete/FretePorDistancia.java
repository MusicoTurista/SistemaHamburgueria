package main.frete;

import main.dominio.Dinheiro;

public class FretePorDistancia implements EstrategiaFrete {
    public Dinheiro calcular(double d, Dinheiro v) {
        return new Dinheiro(3.0 + d * 1.5);
    }

    public String modalidade() {
        return "Motoboy por km";
    }
}
