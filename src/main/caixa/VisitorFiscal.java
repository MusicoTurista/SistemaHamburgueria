package main.caixa;

public interface VisitorFiscal {
    void visitar(ItemPedidoVisitavel i);

    void imprimirResumo();
}
