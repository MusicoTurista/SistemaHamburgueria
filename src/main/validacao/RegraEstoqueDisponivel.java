package main.validacao;

import main.cardapio.flyweight.IngredientePool;
import main.dominio.ItemLanche;
import main.dominio.Pedido;

import java.util.Map;

public class RegraEstoqueDisponivel extends RegraValidacao {
    private static final Map<String, int[]> RECEITAS = Map.of("X-Simples", new int[]{150}, "X-Duplo", new int[]{300}, "Black Angus 180g", new int[]{180}, "Wagyu Smash", new int[]{180});
    private static final Map<String, String> INGREDIENTE_POR_LANCHE = Map.of("X-Simples", "Acém", "X-Duplo", "Acém", "Black Angus 180g", "Wagyu A5", "Wagyu Smash", "Wagyu A5");

    protected boolean checar(Pedido pedido) {
        for (ItemLanche item : pedido.getItens()) {
            String nomeItem = item.descricao().split(" \\+")[0].trim();
            String ingrediente = INGREDIENTE_POR_LANCHE.get(nomeItem);
            if (ingrediente == null) continue;

            int[] receita = RECEITAS.get(nomeItem);
            int gramas = receita[0];

            if (!IngredientePool.temEstoque(ingrediente, gramas)) {
                return false;
            }
        }
        return true;
    }
}
