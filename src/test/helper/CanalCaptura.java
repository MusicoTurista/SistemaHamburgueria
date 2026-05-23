package test.helper;

import main.notificacao.CanalNotificacao;

import java.util.ArrayList;
import java.util.List;

public class CanalCaptura implements CanalNotificacao {
    private final List<String> mensagens = new ArrayList<>();
    private String ultimoDestinatario = "";

    public List<String> getMensagens() {
        return mensagens;
    }

    public String getUltimoDestinatario() {
        return ultimoDestinatario;
    }

    @Override
    public void enviar(String dest, String msg) {
        ultimoDestinatario = dest;
        mensagens.add(msg);
    }

    public String ultima() {
        return mensagens.isEmpty() ? "" : mensagens.getLast();
    }
}