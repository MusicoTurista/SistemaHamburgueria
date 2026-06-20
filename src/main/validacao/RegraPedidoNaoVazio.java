package main.validacao;

import main.dominio.Pedido;

public class RegraPedidoNaoVazio extends RegraValidacao {
    protected boolean checar(Pedido pedido) {
        return !pedido.getItens().isEmpty();
    }
}
