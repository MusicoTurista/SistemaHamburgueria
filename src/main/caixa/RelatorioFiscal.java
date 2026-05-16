package main.caixa;

import main.dominio.Dinheiro;

public class RelatorioFiscal implements VisitorFiscal {
    private Dinheiro lanches = new Dinheiro(0), bebidas = new Dinheiro(0), outros = new Dinheiro(0);
    private int count = 0;

    public void visitar(ItemPedidoVisitavel i) {
        Dinheiro p = i.getLanche().preco();
        count++;
        switch (i.getCategoria()) {
            case "lanche" -> lanches = lanches.somar(p);
            case "bebida" -> bebidas = bebidas.somar(p);
            default -> outros = outros.somar(p);
        }
    }

    public void imprimirResumo() {
        Dinheiro total = lanches.somar(bebidas).somar(outros);
        System.out.println("[Fiscal] " + count + " itens | Lanches: " + lanches + " | Bebidas: " + bebidas + " | Outros: " + outros);
        System.out.println("[Fiscal] ISS 5% sobre lanches: " + lanches.multiplicar(0.05));
        System.out.println("[Fiscal] Total tributável: " + total);
    }
}
