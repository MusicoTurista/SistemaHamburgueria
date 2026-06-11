package main.cardapio.flyweight;

import java.util.HashMap;
import java.util.Map;

public class IngredientePool {
    private static final Map<String, Ingrediente> cache = new HashMap<>();
    private static final Map<String, Integer> estoque = new HashMap<>();

    public static Ingrediente get(String nome, String fornecedor, int cal) {
        return cache.computeIfAbsent(nome, k -> new Ingrediente(k, fornecedor, cal));
    }

    public static boolean temEstoque(String nome, int gramas) {
        return estoque.getOrDefault(nome, 0) >= gramas;
    }

    public static void reservar(String nome, int gramas) {
        estoque.merge(nome, -gramas, Integer::sum);
    }

    public static void repor(String nome, int gramas) {
        estoque.merge(nome, gramas, Integer::sum);
    }

    public static int estoqueAtual(String nome) {
        return estoque.getOrDefault(nome, 0);
    }

    public static int tamanho() {
        return cache.size();
    }
}