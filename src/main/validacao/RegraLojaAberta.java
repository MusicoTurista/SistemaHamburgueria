package main.validacao;

import main.config.Loja;
import main.dominio.Pedido;

public class RegraLojaAberta extends RegraValidacao {
    protected boolean checar(Pedido p) {
        return Loja.getInstance().estaAberta();
    }
}
