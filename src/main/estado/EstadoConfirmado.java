package main.estado;

public class EstadoConfirmado implements EstadoPedido {

    private EstadoConfirmado(){}

    private static EstadoConfirmado instance = new EstadoConfirmado();

    public static EstadoConfirmado getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {
        System.out.println("Já confirmado.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        c.transicionar(EstadoEmPreparo.getInstance());
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Ainda na fila.");
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Ainda na fila.");
    }

    public void cancelar(ContextoPedido c) {
        System.out.println("Já confirmado.");
    }

    public String nome() {
        return "CONFIRMADO";
    }
}
