package main.pagamento;

import main.dominio.Dinheiro;

public class ProxyTerminal implements TerminalPagamento {
    private final TerminalPagamento real;
    private boolean online = true;
    private boolean ocupado = false;

    public ProxyTerminal(TerminalPagamento real) {
        this.real = real;
    }

    public void setOnline(boolean v) {
        this.online = v;
    }

    @Override
    public boolean processar(String forma, Dinheiro valor) {
        if (!online) {
            return false;
        }
        if (ocupado) {
            return false;
        }
        ocupado = true;
        boolean ok = real.processar(forma, valor);
        ocupado = false;
        return ok;
    }
}
