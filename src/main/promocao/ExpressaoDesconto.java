package main.promocao;

import main.dominio.Dinheiro;
import main.dominio.Pedido;

public interface ExpressaoDesconto {
    Dinheiro aplicar(Dinheiro preco, Pedido pedido);
    String   descricao();
}
