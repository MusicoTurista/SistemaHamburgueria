package main.cozinha;

import main.dominio.Pedido;

public class SetorEntrega implements Setor {
    public SetorEntrega(CentralOperacoes c) {
    }

    public String nome() {
        return "ENTREGA";
    }

    public void receberMensagem(String r, String ev, Object d) {
        if ("PEDIDO_PRONTO".equals(ev) && d instanceof Pedido p)
            System.out.println("[Entrega] #" + p.getId() + (p.getDelivery() ? " → rota: " + p.getEnderecoEntrega() : " → balcão"));
    }
}
