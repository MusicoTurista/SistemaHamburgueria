package test.helper;

import main.notificacao.CanalNotificacao;

import java.util.ArrayList;
import java.util.List;

public class CanalCaptura implements CanalNotificacao {
    final List<String> mensagens = new ArrayList<>();
    String ultimoDestinatario = "";

    @Override
    public void enviar(String dest, String msg) {
        ultimoDestinatario = dest;
        mensagens.add(msg);
    }

    public String ultima() {
        return mensagens.isEmpty() ? "" : mensagens.get(mensagens.size() - 1);
    }
}