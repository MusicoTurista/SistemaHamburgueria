package main.notificacao;

public interface CanalNotificacao {
    void enviar(String destinatario, String mensagem);
}