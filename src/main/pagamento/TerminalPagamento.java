package main.pagamento;

import main.dominio.Dinheiro;

public interface TerminalPagamento {
    boolean processar(String forma, Dinheiro valor);
}