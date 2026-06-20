package main.cozinha;

import main.cardapio.flyweight.Ingrediente;
import main.cardapio.flyweight.IngredientePool;
import main.dominio.Pedido;

public class MontadorClassico extends ProcessoMontagem {
    protected void prepararIngredientes(Pedido p) {
        Ingrediente acem = IngredientePool.get("Acém", "Frigorífico SP", 250);
        int gramas = 150 * contarLanches(p);
        IngredientePool.reservar("Acém", gramas);
        System.out.println(acem.usar(gramas) + " | Estoque restante: " + IngredientePool.estoqueAtual("Acém") + "g");
    }

    protected int contarLanches(Pedido p) {
        return (int) p.getItens().stream().filter(i -> i.descricao().split(" \\+")[0].trim().startsWith("X-")).count();
    }

    protected void montarLanche(Pedido p) {
        System.out.println("Pão, Acém, molho da casa");
    }
}