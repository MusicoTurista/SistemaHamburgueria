package main.estado;

public class EstadoAguardando implements EstadoPedido {

    private EstadoAguardando(){}

    private static EstadoAguardando instance = new EstadoAguardando();

    public static EstadoAguardando getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {
        c.transicionar(EstadoConfirmado.getInstance());
    }

    public void iniciarPreparo(ContextoPedido c) {}

    public void finalizar(ContextoPedido c) {}

    public void entregar(ContextoPedido c) {}

    public void cancelar(ContextoPedido c) {
        c.transicionar(EstadoCancelado.getInstance());
    }

    public String nome() {
        return "AGUARDANDO PAGAMENTO";
    }
}
