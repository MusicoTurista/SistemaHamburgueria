package main.promocao;

public class InterpretadorPromocao {
    public static ExpressaoDesconto interpretar(String regra) {
        if (regra == null || regra.isBlank()) return new SemDesconto();
        if (regra.startsWith("DELIVERY_"))
            return new DescontoCondicionalDelivery(interpretar(regra.replace("DELIVERY_", "")));
        if (regra.startsWith("DESCONTO_"))
            return new DescontoPercentual(Integer.parseInt(regra.replace("DESCONTO_", "").replace("PCT", "")) / 100.0);
        return new SemDesconto();
    }
}