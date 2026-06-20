package main.estado;

public class EstadoCancelado implements EstadoPedido {

    private EstadoCancelado(){}

    private static EstadoCancelado instance = new EstadoCancelado();

    public static EstadoCancelado getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {}

    public void iniciarPreparo(ContextoPedido c) {}

    public void finalizar(ContextoPedido c) {}

    public void entregar(ContextoPedido c) {}

    public void cancelar(ContextoPedido c) {}

    public String nome() {
        return "CANCELADO";
    }
}
