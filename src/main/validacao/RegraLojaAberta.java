package main.validacao;

import main.config.Loja;
import main.dominio.Pedido;

public class RegraLojaAberta extends RegraValidacao {
    protected boolean checar(Pedido p) {
        if (!Loja.getInstance().estaAberta()) {
            System.out.println("[Regra] Loja fechada.");
            return false;
        }
        return true;
    }
}
