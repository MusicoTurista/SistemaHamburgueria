package main.promocao;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

public class DescontoPercentual implements ExpressaoDesconto {
    private final double pct;

    public DescontoPercentual(double p) {
        pct = p;
    }

    public Dinheiro aplicar(Dinheiro p, Pedido ped) {
        return p.multiplicar(1 - pct);
    }

    public String descricao() {
        return (int) (pct * 100) + "% de desconto";
    }
}
