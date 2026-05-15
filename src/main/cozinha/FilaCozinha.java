package main.cozinha;

import java.util.ArrayDeque;
import java.util.Deque;

public class FilaCozinha {
    private final Deque<ComandoCozinha> hist = new ArrayDeque<>();

    public void executar(ComandoCozinha c) {
        System.out.println("[Fila] " + c.descricao());
        c.executar();
        hist.push(c);
    }

    public void desfazerUltimo() {
        if (!hist.isEmpty()) {
            ComandoCozinha c = hist.pop();
            System.out.println("[Undo] " + c.descricao());
            c.desfazer();
        }
    }
}
