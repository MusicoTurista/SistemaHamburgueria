package main.notificacao;

import main.dominio.Pedido;

public class PedidoProntoEvento extends EventoPedido {
    public PedidoProntoEvento(CanalNotificacao c) {
        super(c);
    }

    public void notificar(Pedido p) {
        canal.enviar(p.getNomeCliente(), "Pedido #" + p.getId() + " pronto! Retire no balcão.");
    }
}
