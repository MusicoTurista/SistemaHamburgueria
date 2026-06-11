package main.estado;

public class EstadoEntregue implements EstadoPedido {

    private EstadoEntregue(){}

    private static EstadoEntregue instance = new EstadoEntregue();

    public static EstadoEntregue getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {
        System.out.println("Pedido encerrado.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Pedido encerrado.");
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Pedido encerrado.");
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Já entregue.");
    }

    public void cancelar(ContextoPedido c) {
        System.out.println("Já confirmado.");
    }

    public String nome() {
        return "ENTREGUE";
    }
}
