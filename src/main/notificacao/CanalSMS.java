package main.notificacao;

public class CanalSMS implements CanalNotificacao {
    public void enviar(String dest, String msg) {
        System.out.println("[SMS → " + dest + "] " + msg);
    }
}