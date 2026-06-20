package main.atendimento;

import main.config.Loja;
import main.caixa.*;
import main.cozinha.*;
import main.dominio.*;
import main.estado.*;
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
        RegraValidacao r4 = new RegraValorMinimo();
        RegraValidacao r5 = new RegraEstoqueDisponivel();
        r1.encadear(r2).encadear(r3).encadear(r4).encadear(r5);
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

        ContextoPedido ctx = new ContextoPedido(pedido);

        if (!cadeia.validar(pedido)) {
            ctx.cancelar();
            return false;
        }

        ExpressaoDesconto promo = InterpretadorPromocao.interpretar(codigoPromo);
        Dinheiro valorDesconto = promo.aplicar(pedido.subtotal(), pedido);

        ServicoDelivery delivery = new ServicoDelivery(pedido, distKm);
        Dinheiro frete = delivery.calcular(distKm, valorDesconto);
        Dinheiro totalFinal = valorDesconto.somar(frete).multiplicar(1 + Loja.getInstance().taxaServico);

        if (!terminal.processar(pedido.getFormaPagamento(), totalFinal)) {
            ctx.cancelar();
            return false;
        }

        ctx.inscrever(new NotificadorCliente(canalCliente));
        ctx.inscrever(new PainelCozinha());
        ctx.confirmar();
        ctx.iniciarPreparo();

        caixaSetor.confirmarPagamento(pedido);

        ctx.finalizar();
        ctx.entregar();

        new PedidoConfirmado(canalCliente).notificar(pedido);

        sessao.registrar(pedido);
        return true;
    }

    public SessaoCaixa getSessao() {
        return sessao;
    }
}
