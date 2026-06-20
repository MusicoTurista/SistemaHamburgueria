package main.estado;

public class EstadoConfirmado implements EstadoPedido {

    private EstadoConfirmado(){}

    private static EstadoConfirmado instance = new EstadoConfirmado();

    public static EstadoConfirmado getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {}

    public void iniciarPreparo(ContextoPedido c) {
        c.transicionar(EstadoEmPreparo.getInstance());
    }

    public void finalizar(ContextoPedido c) {}

    public void entregar(ContextoPedido c) {}

    public void cancelar(ContextoPedido c) {}

    public String nome() {
        return "CONFIRMADO";
    }
}
