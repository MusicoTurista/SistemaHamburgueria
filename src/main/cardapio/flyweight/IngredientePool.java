package main.cardapio.flyweight;

import java.util.HashMap;
import java.util.Map;

public class IngredientePool {
    private static final Map<String, Ingrediente> cache = new HashMap<>();

    public static Ingrediente get(String nome, String fornecedor, int cal) {
        return cache.computeIfAbsent(nome, k -> new Ingrediente(k, fornecedor, cal));
    }

    public static int tamanho() {
        return cache.size();
    }
}