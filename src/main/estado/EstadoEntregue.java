package main.estado;

public class EstadoEntregue implements EstadoPedido {
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

    public String nome() {
        return "ENTREGUE";
    }
}
