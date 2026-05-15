package main.frete;

import main.dominio.Dinheiro;

public class FreteFixo implements EstrategiaFrete {
    public Dinheiro calcular(double d, Dinheiro v) {
        return new Dinheiro(5.0);
    }

    public String modalidade() {
        return "Motoboy taxa fixa";
    }
}
