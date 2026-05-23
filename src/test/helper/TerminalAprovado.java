package test.helper;

import main.pagamento.TerminalPagamento;
import main.dominio.Dinheiro;

public class TerminalAprovado implements TerminalPagamento {
    private int chamadas = 0;

    public int getChamadas() {
        return chamadas;
    }

    @Override
    public boolean processar(String forma, Dinheiro valor) {
        chamadas++;
        return true;
    }
}