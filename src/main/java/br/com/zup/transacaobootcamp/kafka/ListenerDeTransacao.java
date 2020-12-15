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
            Primeiro foi preciso configurar a anotação KafkaListener no qual é
            necessário informar qual tópico ele irá coletar os eventos, como por exemplo:
            - ${spring.kafka.topic.transactions} (Definido no application.properties)

            Segundo a gente precisou adicionar qual evento a gente iria receber e
            para isso basta passar como parâmetro.
            - public void ouvir(TransacaoEvento eventoDeTransacao)
            "TransacaoEvento foi a classe que eu criei para receber os dados que serão buscados no tópico"

             e toda "mágica" de como tratar o evento
             foi definido na classe KafkaConfiguration!
             */
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoEvento eventoDeTransacao) {
        //No corpo do método eu processo da forma o evento recebido da forma que desejar
        Transacao transacao = eventoDeTransacao.toModel();
        repository.save(transacao);
    }

}
