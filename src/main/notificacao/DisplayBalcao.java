package main.notificacao;

public class DisplayBalcao implements CanalNotificacao {
    public void enviar(String dest, String msg) {
        System.out.println("[Display] " + msg);
    }
}
