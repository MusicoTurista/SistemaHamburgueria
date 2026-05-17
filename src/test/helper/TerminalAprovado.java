package test.helper;

import main.pagamento.TerminalPagamento;
import main.dominio.Dinheiro;

public class TerminalAprovado implements TerminalPagamento {
    int chamadas = 0;

    @Override
    public boolean processar(String forma, Dinheiro valor) {
        chamadas++;
        return true;
    }
}