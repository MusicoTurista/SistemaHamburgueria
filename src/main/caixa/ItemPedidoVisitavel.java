package main.caixa;

import main.dominio.ItemLanche;

public class ItemPedidoVisitavel implements ItemVisitavel {
    private final ItemLanche lanche;
    private final String categoria;

    public ItemPedidoVisitavel(ItemLanche l, String c) {
        lanche = l;
        categoria = c;
    }

    public void aceitar(VisitorFiscal v) {
        v.visitar(this);
    }

    public ItemLanche getLanche() {
        return lanche;
    }

    public String getCategoria() {
        return categoria;
    }
}