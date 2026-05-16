package main.caixa;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

import java.util.ArrayList;
import java.util.List;

public class SessaoCaixa {
    private Dinheiro total = new Dinheiro(0);
    private int numPed = 0;
    private List<String> ids = new ArrayList<>();

    public void registrar(Pedido p) {
        total = total.somar(p.totalComTaxa());
        numPed++;
        ids.add(p.getId());
    }

    static class Snapshot {
        final Dinheiro total;
        final int numPed;
        final List<String> ids;

        Snapshot(Dinheiro t, int n, List<String> i) {
            total = t;
            numPed = n;
            ids = new ArrayList<>(i);
        }
    }

    public Snapshot salvar() {
        return new Snapshot(total, numPed, ids);
    }

    public void restaurar(Snapshot s) {
        total = s.total;
        numPed = s.numPed;
        ids = new ArrayList<>(s.ids);
    }

    @Override
    public String toString() {
        return numPed + " pedidos | Total: " + total;
    }
}
