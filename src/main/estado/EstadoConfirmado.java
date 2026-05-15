package main.estado;

public class EstadoConfirmado implements EstadoPedido {
    public void confirmar(ContextoPedido c) {
        System.out.println("Já confirmado.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        c.transicionar(new EstadoEmPreparo());
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Ainda na fila.");
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Ainda na fila.");
    }

    public String nome() {
        return "CONFIRMADO";
    }
}
