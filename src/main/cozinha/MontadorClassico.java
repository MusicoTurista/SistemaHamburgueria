package main.cozinha;

import main.cardapio.flyweight.IngredientePool;
import main.dominio.Pedido;

public class MontadorClassico extends ProcessoMontagem {
    protected void prepararIngredientes(Pedido p) {
        System.out.println("  → " + IngredientePool.get("Acém", "Frigorífico SP", 250).usar(150));
    }

    protected void montarLanche(Pedido p) {
        System.out.println("  → Pão, Acém, molho da casa");
    }
}