package main.cozinha;

import main.dominio.Pedido;

public abstract class ProcessoMontagem {
    public final void montar(Pedido pedido) {
        aquecer();
        prepararIngredientes(pedido);
        montarLanche(pedido);
        embalar(pedido);
    }

    protected void aquecer() {
        System.out.println("Aquecendo chapa");
    }

    protected void embalar(Pedido p) {
        System.out.println("Embalando em " + (p.getDelivery() ? "caixa delivery" : "bandeja"));
    }

    protected abstract void prepararIngredientes(Pedido p);

    protected abstract void montarLanche(Pedido p);
}