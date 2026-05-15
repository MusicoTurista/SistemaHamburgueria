package main.estado;

public class EstadoEmPreparo implements EstadoPedido {
    public void confirmar(ContextoPedido c) {
        System.out.println("Em preparo.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Já em preparo.");
    }

    public void finalizar(ContextoPedido c) {
        c.transicionar(new EstadoPronto());
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Ainda em preparo.");
    }

    public String nome() {
        return "EM PREPARO";
    }
}