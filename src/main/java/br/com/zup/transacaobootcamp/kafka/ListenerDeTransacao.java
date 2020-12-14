package br.com.zup.transacaobootcamp.kafka;

import br.com.zup.transacaobootcamp.kafka.event.TransacaoEvento;
import br.com.zup.transacaobootcamp.model.Transacao;
import br.com.zup.transacaobootcamp.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    @Autowired
    private TransacaoRepository repository;


    @Deprecated
    public ListenerDeTransacao() {
    }

    /*
            Primeiro a gente precisou configurar a anotação KafkaListener no qual é
            necessário configurar qual tópico ele irá coletar os eventos, como por exemplo:
             ${spring.kafka.topic.transactions}

             Segundo a gente precisou adicionar qual evento a gente iria receber e
             para isso basta passar como parâmetro toda "mágica" de como tratar o evento
             foi definido na classe KafkaConfiguration!
             */
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoEvento eventoDeTransacao) {
        Transacao transacao = eventoDeTransacao.toModel();
        repository.save(transacao);
    }

}
