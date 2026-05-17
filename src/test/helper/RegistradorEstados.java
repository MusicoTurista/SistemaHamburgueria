package test.helper;

import main.dominio.Pedido;
import main.estado.ObservadorPedido;

import java.util.ArrayList;
import java.util.List;

public class RegistradorEstados implements ObservadorPedido {
    final List<String> estados = new ArrayList<>();

    @Override
    public void aoMudarEstado(Pedido p, String estado) {
        estados.add(estado);
    }
}