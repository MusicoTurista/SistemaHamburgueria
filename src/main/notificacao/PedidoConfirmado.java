package main.notificacao;

import main.dominio.Pedido;

public class PedidoConfirmado extends EventoPedido {
    public PedidoConfirmado(CanalNotificacao c) {
        super(c);
    }

    public void notificar(Pedido p) {
        canal.enviar(p.getNomeCliente(), "Pedido #" + p.getId() + " confirmado! Total: " + p.totalComTaxa());
    }
}
