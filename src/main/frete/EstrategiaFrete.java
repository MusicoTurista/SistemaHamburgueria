package main.frete;

import main.dominio.Dinheiro;

public interface EstrategiaFrete {
    Dinheiro calcular(double distKm, Dinheiro valorPedido);

    String modalidade();
}