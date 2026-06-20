package main.validacao;

import main.dominio.Pedido;

public class RegraEnderecoDelivery extends RegraValidacao {
    protected boolean checar(Pedido p) {
        if (p.getDelivery() && p.getEnderecoEntrega().isBlank()) {
            return false;
        }
        return true;
    }
}
