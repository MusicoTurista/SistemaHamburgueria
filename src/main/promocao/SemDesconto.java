package main.promocao;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

public class SemDesconto implements ExpressaoDesconto {
    public Dinheiro aplicar(Dinheiro p, Pedido ped) {
        return p;
    }

    public String descricao() {
        return "Sem desconto";
    }
}
