package main.notificacao;

public class CanalWhatsapp implements CanalNotificacao {
    public void enviar(String dest, String msg) {
        System.out.println("[WhatsApp → " + dest + "] " + msg);
    }
}
