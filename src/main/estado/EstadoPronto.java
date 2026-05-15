package main.estado;

public class EstadoPronto implements EstadoPedido {
    public void confirmar(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public void entregar(ContextoPedido c) {
        c.transicionar(new EstadoEntregue());
    }

    public String nome() {
        return "PRONTO";
    }
}