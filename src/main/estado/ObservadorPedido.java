package main.estado;

import main.dominio.Pedido;

public interface ObservadorPedido {
    void aoMudarEstado(Pedido pedido, String novoEstado);
}