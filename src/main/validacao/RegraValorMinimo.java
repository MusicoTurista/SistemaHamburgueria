package main.validacao;

import main.config.Loja;
import main.dominio.Dinheiro;
import main.dominio.Pedido;

public class RegraValorMinimo extends RegraValidacao {

    public RegraValorMinimo() {}

    protected boolean checar(Pedido p) {
        return !(p.subtotal().reais() < Loja.getInstance().pedidoMinimo);
    }
}
