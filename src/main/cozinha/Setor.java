package main.cozinha;

public interface Setor {
    void receberMensagem(String rem, String evento, Object dados);

    String nome();
}
