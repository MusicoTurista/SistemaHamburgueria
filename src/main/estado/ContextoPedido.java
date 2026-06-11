package main.estado;

import main.dominio.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ContextoPedido {
    private EstadoPedido estado;
    private final Pedido pedido;
    private final List<ObservadorPedido> obs = new ArrayList<>();

    public ContextoPedido(Pedido p) {
        this.pedido = p;
        this.estado = EstadoAguardando.getInstance();
    }

    public void inscrever(ObservadorPedido o) {
        obs.add(o);
    }

    void transicionar(EstadoPedido novo) {
        this.estado = novo;
        System.out.println("[State] #" + pedido.getId() + " → " + novo.nome());
        obs.forEach(o -> o.aoMudarEstado(pedido, novo.nome()));
    }

    public void confirmar() {
        estado.confirmar(this);
    }

    public void iniciarPreparo() {
        estado.iniciarPreparo(this);
    }

    public void finalizar() {
        estado.finalizar(this);
    }

    public void entregar() {
        estado.entregar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }



    public String estadoAtual() {
        return estado.nome();
    }
}
