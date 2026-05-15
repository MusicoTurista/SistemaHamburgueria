package main.notificacao;

import main.dominio.Pedido;

public abstract class EventoPedido {
    protected CanalNotificacao canal;

    public EventoPedido(CanalNotificacao canal) {
        this.canal = canal;
    }

    public abstract void notificar(Pedido pedido);
}
