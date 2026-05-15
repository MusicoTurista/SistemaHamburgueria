package main.validacao;

import main.dominio.Pedido;

public class RegraPedidoNaoVazio extends RegraValidacao {
    protected boolean checar(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("[Regra] Pedido vazio.");
            return false;
        }
        return true;
    }
}
