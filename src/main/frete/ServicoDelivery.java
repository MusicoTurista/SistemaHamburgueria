package main.frete;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

public class ServicoDelivery {
    private final EstrategiaFrete estrategia;

    public ServicoDelivery(Pedido pedido, double distKm) {
        if (!pedido.getDelivery()) estrategia = new FreteGratis();
        else if (distKm <= 2.0) estrategia = new FreteFixo();
        else estrategia = new FretePorDistancia();
    }

    public Dinheiro calcular(double distKm, Dinheiro valor) {
        Dinheiro frete = estrategia.calcular(distKm, valor);
        System.out.println("[Frete] " + estrategia.modalidade() + " → " + frete);
        return frete;
    }
}