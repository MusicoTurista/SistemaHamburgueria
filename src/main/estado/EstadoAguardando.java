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

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Aguardando pagamento.");
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Aguardando pagamento.");
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Aguardando pagamento.");
    }

    public void cancelar(ContextoPedido c) {
        c.transicionar(EstadoCancelado.getInstance());
    }

    public String nome() {
        return "AGUARDANDO PAGAMENTO";
    }
}
