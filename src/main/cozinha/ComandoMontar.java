package main.cozinha;

import main.dominio.Pedido;

public class ComandoMontar implements ComandoCozinha {
    private final ProcessoMontagem montador;
    private final Pedido pedido;
    private boolean feito = false;

    public ComandoMontar(ProcessoMontagem m, Pedido p) {
        montador = m;
        pedido = p;
    }

    public void executar() {
        montador.montar(pedido);
        feito = true;
    }

    public void desfazer() {
        if (feito) System.out.println("[Cancelado] Descartando #" + pedido.getId());
    }

    public String descricao() {
        return "Montar #" + pedido.getId();
    }
}
