package main.cozinha;

import main.dominio.Pedido;

public class ComandoReimprimir implements ComandoCozinha {
    private final Pedido pedido;

    public ComandoReimprimir(Pedido p) {
        pedido = p;
    }

    public void executar() {
        pedido.getItens().forEach(i -> System.out.println("    - " + i.descricao() + "  " + i.preco()));
    }

    public void desfazer() {}

    public String descricao() {
        return "Reimprimir #" + pedido.getId();
    }
}
