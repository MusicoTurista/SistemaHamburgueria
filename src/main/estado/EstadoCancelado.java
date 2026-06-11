package main.estado;

public class EstadoCancelado implements EstadoPedido {

    private EstadoCancelado(){}

    private static EstadoCancelado instance = new EstadoCancelado();

    public static EstadoCancelado getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {
        System.out.println("Pedido cancelado.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Pedido cancelado.");
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Pedido cancelado.");
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Pedido cancelado.");
    }

    public void cancelar(ContextoPedido c) {
        System.out.println("Já cancelado.");
    }

    public String nome() {
        return "CANCELADO";
    }
}
