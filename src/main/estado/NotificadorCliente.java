package main.estado;

import main.dominio.Pedido;
import main.notificacao.CanalNotificacao;

public class NotificadorCliente implements ObservadorPedido {
    private final CanalNotificacao canal;

    public NotificadorCliente(CanalNotificacao c) {
        this.canal = c;
    }

    public void aoMudarEstado(Pedido pedido, String estado) {
        if ("PRONTO".equals(estado))
            canal.enviar(pedido.getNomeCliente(), "Pedido #" + pedido.getId() + " pronto para retirada!");
        if ("ENTREGUE".equals(estado))
            canal.enviar(pedido.getNomeCliente(), "Bom apetite! #" + pedido.getId() + " entregue.");
    }
}
