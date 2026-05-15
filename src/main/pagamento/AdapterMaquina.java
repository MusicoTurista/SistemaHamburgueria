package main.pagamento;

import main.dominio.Dinheiro;

public class AdapterMaquina implements TerminalPagamento {
    private final MaquinaLegado maquina = new MaquinaLegado();

    @Override
    public boolean processar(String forma, Dinheiro valor) {
        String tipo = "credito".equals(forma) ? "CREDIT" : "DEBIT";
        return maquina.executarTransacao(tipo, (long) (valor.reais() * 100)) == 0;
    }
}