package main.estado;

public class EstadoEntregue implements EstadoPedido {

    private EstadoEntregue(){}

    private static EstadoEntregue instance = new EstadoEntregue();

    public static EstadoEntregue getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {}

    public void iniciarPreparo(ContextoPedido c) {}

    public void finalizar(ContextoPedido c) {}

    public void entregar(ContextoPedido c) {}

    public void cancelar(ContextoPedido c) {}

    public String nome() {
        return "ENTREGUE";
    }
}
