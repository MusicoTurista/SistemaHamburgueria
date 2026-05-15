package main.validacao;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

public class RegraValorMinimo extends RegraValidacao {
    private final double min;

    public RegraValorMinimo(double min) {
        this.min = min;
    }

    protected boolean checar(Pedido p) {
        if (p.subtotal().reais() < min) {
            System.out.println("[Regra] Valor mínimo " + new Dinheiro(min) + " não atingido.");
            return false;
        }
        return true;
    }
}
