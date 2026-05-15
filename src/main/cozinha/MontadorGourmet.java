package main.cozinha;

import main.cardapio.flyweight.IngredientePool;
import main.dominio.Pedido;

public class MontadorGourmet extends ProcessoMontagem {
    protected void prepararIngredientes(Pedido p) {
        System.out.println("  → " + IngredientePool.get("Wagyu A5", "Importadora JP", 280).usar(180));
    }

    protected void montarLanche(Pedido p) {
        System.out.println("  → Brioche, Wagyu, molho trufado");
    }
}
