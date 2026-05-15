package main.validacao;

import main.dominio.Pedido;

public abstract class RegraValidacao {
    protected RegraValidacao proximo;

    public RegraValidacao encadear(RegraValidacao prox) {
        proximo = prox;
        return proximo;
    }

    public final boolean validar(Pedido pedido) {
        if (!checar(pedido)) return false;
        return proximo == null || proximo.validar(pedido);
    }

    protected abstract boolean checar(Pedido pedido);
}
