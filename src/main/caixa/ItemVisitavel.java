package main.caixa;

import main.dominio.ItemLanche;

public interface ItemVisitavel {
    void aceitar(VisitorFiscal v);

    ItemLanche getLanche();
}
