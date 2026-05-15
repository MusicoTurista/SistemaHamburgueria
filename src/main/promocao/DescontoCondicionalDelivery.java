package main.promocao;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

public class DescontoCondicionalDelivery implements ExpressaoDesconto {
    private final ExpressaoDesconto base;

    public DescontoCondicionalDelivery(ExpressaoDesconto b) {
        base = b;
    }

    public Dinheiro aplicar(Dinheiro p, Pedido ped) {
        return ped.getDelivery() ? base.aplicar(p, ped) : p;
    }

    public String descricao() {
        return base.descricao() + " (somente delivery)";
    }
}