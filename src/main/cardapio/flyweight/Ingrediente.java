package main.cardapio.flyweight;

import java.util.HashMap;
import java.util.Map;

public class Ingrediente {
    public final String nome;
    public final String fornecedor;
    public final int caloriasPor100g;

    Ingrediente(String n, String f, int c) {
        nome = n;
        fornecedor = f;
        caloriasPor100g = c;
    }

    public String usar(int g) {
        return String.format("%s (%dg, %dkcal) [%s]", nome, g, caloriasPor100g * g / 100, fornecedor);
    }
}
