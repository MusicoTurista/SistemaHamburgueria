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
            System.out.println("    [Proxy] Terminal offline.");
            return false;
        }
        if (ocupado) {
            System.out.println("    [Proxy] Terminal ocupado.");
            return false;
        }
        ocupado = true;
        System.out.println("    [Proxy] Autorizando " + valor + " via " + forma);
        boolean ok = real.processar(forma, valor);
        ocupado = false;
        return ok;
    }
}
