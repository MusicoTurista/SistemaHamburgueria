package main.estado;

import main.dominio.Pedido;

public class PainelCozinha implements ObservadorPedido {
    public void aoMudarEstado(Pedido p, String estado) {
        if ("CONFIRMADO".equals(estado)) System.out.println("[Painel Cozinha] Novo pedido: #" + p.getId());
        if ("EM PREPARO".equals(estado)) System.out.println("[Painel Cozinha] Iniciando: #" + p.getId());
    }
}