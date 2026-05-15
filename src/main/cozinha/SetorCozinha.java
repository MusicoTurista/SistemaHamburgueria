package main.cozinha;

import main.dominio.Pedido;

public class SetorCozinha implements Setor {
    private final CentralOperacoes central;
    private final FilaCozinha fila;

    public SetorCozinha(CentralOperacoes c, FilaCozinha f) {
        central = c;
        fila = f;
    }

    public String nome() {
        return "COZINHA";
    }

    public void receberMensagem(String r, String ev, Object d) {
        if ("PEDIDO_PAGO".equals(ev) && d instanceof Pedido p) {
            System.out.println("    [Cozinha] Recebeu pedido #" + p.getId());
            boolean gourmet = p.getItens().stream().anyMatch(i -> i.preco().reais() > 30);
            ProcessoMontagem m = gourmet ? new MontadorGourmet() : new MontadorClassico();
            fila.executar(new ComandoMontar(m, p));
            central.publicar(nome(), "PEDIDO_PRONTO", p);
        }
    }
}
