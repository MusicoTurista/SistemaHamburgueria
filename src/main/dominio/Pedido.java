package main.dominio;

import main.config.Loja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Pedido {
    String id;
    String nomeCliente;
    List<ItemLanche> itens;
    boolean delivery;
    String enderecoEntrega;
    String formaPagamento;
    String observacoes;

    Pedido(PedidoBuilder builder) {
        this.id = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.nomeCliente = builder.nomeCliente;
        this.itens = Collections.unmodifiableList(new ArrayList<>(builder.itens));
        this.delivery = builder.delivery;
        this.enderecoEntrega = builder.enderecoEntrega;
        this.formaPagamento = builder.formaPagamento;
        this.observacoes = builder.observacoes;
    }

    public Dinheiro subtotal() {
        Dinheiro t = new Dinheiro(0);
        for (ItemLanche i : itens) t = t.somar(i.preco());
        return t;
    }

    public Dinheiro totalComTaxa() {
        return subtotal().multiplicar(1 + Loja.getInstance().taxaServico);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getId() {
        return id;
    }

    public List<ItemLanche> getItens() {
        return itens;
    }

    public boolean getDelivery(){
        return delivery;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return String.format("Pedido #%s | %s | %d item(ns) | %s | delivery=%b", id, nomeCliente, itens.size(), totalComTaxa(), delivery);
    }
}
