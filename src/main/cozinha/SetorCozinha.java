package main.cozinha;

import main.cardapio.factory.factoryMethod.LinhaGourmet;
import main.cardapio.factory.factoryMethod.LinhaLanche;
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
            String nomePrincipal = p.getItens().stream().map(i -> i.descricao().split(" \\+")[0].trim()).filter(n -> !n.equals("Refrigerante") && !n.equals("Suco Natural")).findFirst().orElse("X-Simples");

            LinhaLanche linha = RegistroLinhas.detectar(nomePrincipal);
            ProcessoMontagem montador = linha.criarMontador();


            fila.executar(new ComandoMontar(montador, p));
            central.publicar(nome(), "PEDIDO_PRONTO", p);
        }
    }
}
