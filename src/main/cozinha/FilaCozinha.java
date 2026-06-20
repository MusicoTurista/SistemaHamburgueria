package main.cozinha;

import java.util.ArrayDeque;
import java.util.Deque;

public class FilaCozinha {
    private final Deque<ComandoCozinha> hist = new ArrayDeque<>();

    public void executar(ComandoCozinha c) {
        c.executar();
        hist.push(c);
    }

    public void desfazerUltimo() {
        if (!hist.isEmpty()) {
            ComandoCozinha c = hist.pop();
            c.desfazer();
        }
    }
}
