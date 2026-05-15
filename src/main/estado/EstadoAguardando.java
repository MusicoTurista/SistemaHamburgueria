package main.estado;

public class EstadoAguardando implements EstadoPedido {
    public void confirmar(ContextoPedido c) {
        c.transicionar(new EstadoConfirmado());
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Aguardando pagamento.");
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Aguardando pagamento.");
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Aguardando pagamento.");
    }

    public String nome() {
        return "AGUARDANDO PAGAMENTO";
    }
}
