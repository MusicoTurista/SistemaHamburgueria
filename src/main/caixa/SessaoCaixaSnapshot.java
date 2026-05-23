package main.caixa;

import main.dominio.Dinheiro;

import java.util.ArrayList;
import java.util.List;

public class SessaoCaixaSnapshot {
    final Dinheiro total;
    final int numPed;
    final List<String> ids;

    SessaoCaixaSnapshot(Dinheiro t, int n, List<String> i) {
        total = t;
        numPed = n;
        ids = new ArrayList<>(i);
    }

    public Dinheiro getTotal() {
        return total;
    }

    public int getNumPed() {
        return numPed;
    }
}
