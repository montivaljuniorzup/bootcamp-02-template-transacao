package br.com.zup.transacaobootcamp.kafka.event;

import br.com.zup.transacaobootcamp.model.Cartao;
import br.com.zup.transacaobootcamp.model.Estabelecimento;
import br.com.zup.transacaobootcamp.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoEvento {

    @NotBlank
    private String id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private EstabelecimentoEvento estabelecimento;

    @NotNull
    private CartaoEvento cartao;

    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoEvento() {
    }

    public TransacaoEvento(@NotBlank String id,
                           @NotNull BigDecimal valor,
                           @NotNull EstabelecimentoEvento estabelecimento,
                           @NotNull CartaoEvento cartao,
                           @NotNull LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public Transacao toModel() {
        Estabelecimento estabelecimentoModel = this.estabelecimento.toModel();
        Cartao cartaoModel = this.cartao.toModel();
        return new Transacao(this.id, this.valor, estabelecimentoModel, cartaoModel, this.efetivadaEm);
    }


    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoEvento getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoEvento getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
