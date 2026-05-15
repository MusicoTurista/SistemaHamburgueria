package main.cardapio.flyweight;

import java.util.HashMap;
import java.util.Map;

public class Ingrediente {
    public final String nome;
    public final String fornecedor;
    public final int caloriasPor100g;

    private Ingrediente(String n, String f, int c) {
        nome = n;
        fornecedor = f;
        caloriasPor100g = c;
    }

    public String usar(int g) {
        return String.format("%s (%dg, %dkcal) [%s]", nome, g, caloriasPor100g * g / 100, fornecedor);
    }

    static class Pool {
        private static final Map<String, Ingrediente> cache = new HashMap<>();

        public static Ingrediente get(String nome, String fornecedor, int cal) {
            return cache.computeIfAbsent(nome, k -> new Ingrediente(k, fornecedor, cal));
        }

        public static int tamanho() {
            return cache.size();
        }
    }
}
