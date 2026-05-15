package main.dominio;

import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {
    String nomeCliente = "Cliente";
    List<ItemLanche> itens = new ArrayList<>();
    boolean delivery = false;
    String enderecoEntrega = "";
    String formaPagamento = "dinheiro";
    String observacoes = "";

    public PedidoBuilder cliente(String n) {
        nomeCliente = n;
        return this;
    }

    public PedidoBuilder item(ItemLanche i) {
        itens.add(i);
        return this;
    }

    public PedidoBuilder delivery(String end) {
        delivery = true;
        enderecoEntrega = end;
        return this;
    }

    public PedidoBuilder pagamento(String f) {
        formaPagamento = f;
        return this;
    }

    public PedidoBuilder observacoes(String o) {
        observacoes = o;
        return this;
    }

    public Pedido build() {
        return new Pedido(this);
    }
}

