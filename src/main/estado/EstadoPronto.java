package main.estado;

public class EstadoPronto implements EstadoPedido {

    private EstadoPronto(){}

    private static EstadoPronto instance = new EstadoPronto();

    public static EstadoPronto getInstance() {
        return instance;
    }
    public void confirmar(ContextoPedido c) {}

    public void iniciarPreparo(ContextoPedido c) {}

    public void finalizar(ContextoPedido c) {}

    public void entregar(ContextoPedido c) {
        c.transicionar(EstadoEntregue.getInstance());
    }

    public void cancelar(ContextoPedido c) {}

    public String nome() {
        return "PRONTO";
    }
}