package main.estado;

public class EstadoEmPreparo implements EstadoPedido {

    private EstadoEmPreparo(){}

    private static EstadoEmPreparo instance = new EstadoEmPreparo();

    public static EstadoEmPreparo getInstance() {
        return instance;
    }

    public void confirmar(ContextoPedido c) {
        System.out.println("Em preparo.");
    }

    public void iniciarPreparo(ContextoPedido c) {
        System.out.println("Já em preparo.");
    }

    public void finalizar(ContextoPedido c) {
        c.transicionar(EstadoPronto.getInstance());
    }

    public void entregar(ContextoPedido c) {
        System.out.println("Ainda em preparo.");
    }

    public void cancelar(ContextoPedido c) {
        System.out.println("Já confirmado.");
    }

    public String nome() {
        return "EM PREPARO";
    }
}