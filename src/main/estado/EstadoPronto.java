package main.estado;

public class EstadoPronto implements EstadoPedido {

    private EstadoPronto(){}

    private static EstadoPronto instance = new EstadoPronto();

    public static EstadoPronto getInstance() {
        return instance;
    }
    public void confirmar(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public void finalizar(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public void entregar(ContextoPedido c) {
        c.transicionar(EstadoEntregue.getInstance());
    }

    public void cancelar(ContextoPedido c) {
        System.out.println("Já pronto.");
    }

    public String nome() {
        return "PRONTO";
    }
}