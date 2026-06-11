package main.cozinha;

import main.cardapio.factory.factoryMethod.LinhaClassica;
import main.cardapio.factory.factoryMethod.LinhaGourmet;
import main.cardapio.factory.factoryMethod.LinhaLanche;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegistroLinhas {
    private static final Map<String, LinhaLanche> linhas = new LinkedHashMap<>();

    static {
        linhas.put("classica", new LinhaClassica());
        linhas.put("gourmet", new LinhaGourmet());
    }

    public static LinhaLanche get(String nome) {
        return linhas.getOrDefault(nome, linhas.get("classica"));
    }

    public static LinhaLanche detectar(String nomeLanche) {
        if (nomeLanche.contains("Wagyu") || nomeLanche.contains("Black Angus")) return linhas.get("gourmet");
        return linhas.get("classica");
    }

}
