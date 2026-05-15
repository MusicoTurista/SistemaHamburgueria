package main.cozinha;

import java.util.LinkedHashMap;
import java.util.Map;

public class CentralOperacoes {
    private final Map<String, Setor> setores = new LinkedHashMap<>();

    public void registrar(Setor s) {
        setores.put(s.nome(), s);
    }

    public void publicar(String remetente, String evento, Object dados) {
        System.out.println("[Central] " + remetente + " → " + evento);
        setores.values().stream()
            .filter(s -> !s.nome().equals(remetente))
            .forEach(s -> s.receberMensagem(remetente, evento, dados));
    }
}
