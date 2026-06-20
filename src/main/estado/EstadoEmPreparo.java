package main.estado;

public class EstadoEmPreparo implements EstadoPedido {

    private EstadoEmPreparo(){}

    private static EstadoEmPreparo instance = new EstadoEmPreparo();

    public static EstadoEmPreparo getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {}

    public void iniciarPreparo(ContextoPedido c) {}

    public void finalizar(ContextoPedido c) {
        c.transicionar(EstadoPronto.getInstance());
    }

    public void entregar(ContextoPedido c) {}

    public void cancelar(ContextoPedido c) {}

    public String nome() {
        return "EM PREPARO";
    }
}