package test.helper;

import main.pagamento.TerminalPagamento;
import main.dominio.Dinheiro;

public class TerminalRecusado implements TerminalPagamento {
    @Override
    public boolean processar(String forma, Dinheiro valor) {
        return false;
    }
}
