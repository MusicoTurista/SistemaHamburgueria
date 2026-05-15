package main.dominio;

import main.config.Loja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Pedido {
    final String id;
    final String nomeCliente;
    final List<ItemLanche> itens;
    final boolean delivery;
    final String enderecoEntrega;
    final String formaPagamento;
    final String observacoes;

    private Pedido(Builder b) {
        this.id = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.nomeCliente = b.nomeCliente;
        this.itens = Collections.unmodifiableList(new ArrayList<>(b.itens));
        this.delivery = b.delivery;
        this.enderecoEntrega = b.enderecoEntrega;
        this.formaPagamento = b.formaPagamento;
        this.observacoes = b.observacoes;
    }

    public Dinheiro subtotal() {
        Dinheiro t = new Dinheiro(0);
        for (ItemLanche i : itens) t = t.somar(i.preco());
        return t;
    }

    public Dinheiro totalComTaxa() {
        return subtotal().multiplicar(1 + Loja.getInstance().taxaServico);
    }

    @Override
    public String toString() {
        return String.format("Pedido #%s | %s | %d item(ns) | %s | delivery=%b",
                id, nomeCliente, itens.size(), totalComTaxa(), delivery);
    }

    static class Builder {
        private String nomeCliente = "Cliente";
        private List<ItemLanche> itens = new ArrayList<>();
        private boolean delivery = false;
        private String enderecoEntrega = "";
        private String formaPagamento = "dinheiro";
        private String observacoes = "";

        public Builder cliente(String n) {
            nomeCliente = n;
            return this;
        }

        public Builder item(ItemLanche i) {
            itens.add(i);
            return this;
        }

        public Builder delivery(String end) {
            delivery = true;
            enderecoEntrega = end;
            return this;
        }

        public Builder pagamento(String f) {
            formaPagamento = f;
            return this;
        }

        public Builder observacoes(String o) {
            observacoes = o;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }
}
