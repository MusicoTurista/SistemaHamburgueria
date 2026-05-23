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

    public SessaoCaixaSnapshot salvar() {
        return new SessaoCaixaSnapshot(total, numPed, ids);
    }

    public void restaurar(SessaoCaixaSnapshot s) {
        total = s.total;
        numPed = s.numPed;
        ids = new ArrayList<>(s.ids);
    }

    @Override
    public String toString() {
        return numPed + " pedidos | Total: " + total;
    }
}
