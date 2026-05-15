package main.pagamento;

public class MaquinaLegado {
    public int executarTransacao(String tipo, long centavos) {
        System.out.println("[Maquina] " + tipo + " de " + String.format("R$ %.2f", centavos / 100.0) + " → aprovado");
        return 0;
    }
}
