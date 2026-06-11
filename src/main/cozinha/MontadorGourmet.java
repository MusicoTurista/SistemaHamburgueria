package main.cozinha;

import main.cardapio.flyweight.Ingrediente;
import main.cardapio.flyweight.IngredientePool;
import main.dominio.Pedido;

public class MontadorGourmet extends ProcessoMontagem {
    protected void prepararIngredientes(Pedido p) {
        Ingrediente wagyu = IngredientePool.get("Wagyu A5", "Importadora JP", 280);
        int gramas = 180 * contarLanches(p);

        IngredientePool.reservar("Wagyu A5", gramas);
        System.out.println("→ " + wagyu.usar(gramas) + " | Estoque restante: " + IngredientePool.estoqueAtual("Wagyu A5") + "g");
    }

    protected void montarLanche(Pedido p) {
        System.out.println("→ Brioche, Wagyu, molho trufado");
    }

    private int contarLanches(Pedido p) {
        return (int) p.getItens().stream().filter(i -> {
            String nome = i.descricao().split(" \\+")[0].trim();
            return nome.equals("Wagyu Smash") || nome.equals("Black Angus 180g");
        }).count();
    }
}
