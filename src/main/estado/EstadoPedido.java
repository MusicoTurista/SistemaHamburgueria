package main.estado;

public interface EstadoPedido {
    void confirmar(ContextoPedido ctx);

    void iniciarPreparo(ContextoPedido ctx);

    void finalizar(ContextoPedido ctx);

    void entregar(ContextoPedido ctx);

    String nome();
}
