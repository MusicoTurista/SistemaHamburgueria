package main.cozinha;

import main.dominio.Pedido;

public class SetorCaixa implements Setor {
    private final CentralOperacoes central;

    public SetorCaixa(CentralOperacoes c) {
        this.central = c;
    }

    public String nome() {
        return "CAIXA";
    }

    public void confirmarPagamento(Pedido p) {
        central.publicar(nome(), "PEDIDO_PAGO", p);
    }

    public void receberMensagem(String r, String ev, Object d) {
        if ("PEDIDO_PRONTO".equals(ev) && d instanceof Pedido p)
            System.out.println("[Caixa] Avisa cliente: pedido #" + p.getId() + " pronto");
    }
}
