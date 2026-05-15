package main.dominio;

public class Pedido {
    class LancheBase implements ItemLanche {
        private final String nome;
        private final Dinheiro preco;

        public LancheBase(String nome, double preco) {
            this.nome = nome;
            this.preco = new Dinheiro(preco);
        }

        @Override
        public String descricao() {
            return nome;
        }

        @Override
        public Dinheiro preco() {
            return preco;
        }

        @Override
        public ItemLanche clonar() {
            try {
                return (LancheBase) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
