package main.cardapio.factory.factoryMethod;

import main.cardapio.Combo;
import main.cardapio.ItemAvulso;
import main.cardapio.decorator.ComBacon;
import main.cardapio.decorator.ComQueijo;
import main.cardapio.factory.abstractFactory.Batata;
import main.cardapio.factory.abstractFactory.FabricaAcompanhamentos;
import main.cardapio.factory.abstractFactory.Molho;
import main.cozinha.ProcessoMontagem;
import main.dominio.ItemLanche;

public abstract class LinhaLanche {
    public abstract ItemLanche criarLanche(String tipo);

    public abstract FabricaAcompanhamentos getFabricaAcompanhamentos();

    public abstract ProcessoMontagem criarMontador();

    public ItemLanche pedirLanche(String tipo, boolean queijo, boolean bacon) {
        ItemLanche lanche = criarLanche(tipo);
        if (queijo) lanche = new ComQueijo(lanche);
        if (bacon) lanche = new ComBacon(lanche);
        return lanche;
    }

    public Combo montarCombo(String nomeLanche, String tipoLanche) {
        FabricaAcompanhamentos fab = getFabricaAcompanhamentos();
        Batata batata = fab.criarBatata();
        Molho molho = fab.criarMolho();
        Combo combo = new Combo("Combo " + nomeLanche, 0.10);
        combo.adicionar(criarLanche(tipoLanche));
        combo.adicionar(new ItemAvulso(batata.descricao() + " c/ " + molho.descricao(), batata.preco().reais()));
        combo.adicionar(new ItemAvulso("Refrigerante", 6.0));
        return combo;
    }
}
