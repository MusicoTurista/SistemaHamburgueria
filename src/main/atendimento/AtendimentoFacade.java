package main.atendimento;

import main.*;
import main.caixa.*;
import main.config.Loja;
import main.cozinha.*;
import main.dominio.*;
import main.estado.ContextoPedido;
import main.estado.NotificadorCliente;
import main.estado.PainelCozinha;
import main.frete.*;
import main.notificacao.*;
import main.pagamento.*;
import main.promocao.*;
import main.validacao.*;

public class AtendimentoFacade {
    private final RegraValidacao cadeia;
    private final TerminalPagamento terminal;
    private final CentralOperacoes central;
    private final CanalNotificacao canalCliente;
    private final SessaoCaixa sessao = new SessaoCaixa();
    private final SetorCaixa caixaSetor;

    public AtendimentoFacade(CanalNotificacao canal) {

        RegraValidacao r1 = new RegraLojaAberta();
        RegraValidacao r2 = new RegraPedidoNaoVazio();
        RegraValidacao r3 = new RegraEnderecoDelivery();
        RegraValidacao r4 = new RegraValorMinimo(15.0);
        r1.encadear(r2).encadear(r3).encadear(r4);
        this.cadeia = r1;

        this.terminal = new ProxyTerminal(new AdapterMaquina());

        FilaCozinha fila = new FilaCozinha();
        this.central = new CentralOperacoes();
        this.caixaSetor = new SetorCaixa(central);
        central.registrar(caixaSetor);
        central.registrar(new SetorCozinha(central, fila));
        central.registrar(new SetorEntrega(central));

        this.canalCliente = canal;
    }

    public boolean processarPedido(Pedido pedido, String codigoPromo, double distKm) {
        System.out.println("\n══════════════════════════════════════");
        System.out.println(pedido);
        System.out.println("══════════════════════════════════════");

        System.out.println("-- Validação --");
        if (!cadeia.validar(pedido)) {
            System.out.println("Pedido REJEITADO.");
            return false;
        }
        System.out.println("OK");

        ExpressaoDesconto promo = InterpretadorPromocao.interpretar(codigoPromo);
        Dinheiro valorDesconto = promo.aplicar(pedido.subtotal(), pedido);
        System.out.println("Promoção: " + promo.descricao() + " → " + valorDesconto);

        System.out.println("-- Frete --");
        ServicoDelivery delivery = new ServicoDelivery(pedido, distKm);
        Dinheiro frete = delivery.calcular(distKm, valorDesconto);
        Dinheiro totalFinal = valorDesconto.somar(frete).multiplicar(1 + Loja.getInstance().taxaServico);
        System.out.println("Total com taxa: " + totalFinal);

        System.out.println("-- Pagamento --");
        if (!terminal.processar(pedido.getFormaPagamento(), totalFinal)) {
            System.out.println("Pagamento RECUSADO.");
            return false;
        }

        System.out.println("-- Ciclo de Vida --");
        ContextoPedido ctx = new ContextoPedido(pedido);
        ctx.inscrever(new NotificadorCliente(canalCliente));
        ctx.inscrever(new PainelCozinha());
        ctx.confirmar();
        ctx.iniciarPreparo();

        System.out.println("-- Mediador --");
        caixaSetor.confirmarPagamento(pedido);

        ctx.finalizar();
        ctx.entregar();

        new PedidoConfirmado(canalCliente).notificar(pedido);

        sessao.registrar(pedido);
        System.out.println("Sessão atual: " + sessao);
        return true;
    }

    public SessaoCaixa getSessao() {
        return sessao;
    }
}
