package br.com.zup.transacaobootcamp.kafka.configuration;

import br.com.zup.transacaobootcamp.kafka.event.TransacaoEvento;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    private final KafkaProperties kafkaProperties;

    public KafkaConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    // Adicionar as propriedades do nosso consumidor (As mesmas do app.properties)
    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getKeyDeserializer());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getValueDeserializer());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());

        return properties;
    }

    /* ConsumerFactory que deve ser configurado em duas etapas:
    A primeira etapa:
    - A gente tem que definir qual será o desserializador da chave e do evento/mensagem,
    como por exemplo, StringDeserializer (Chave), JsonDeserializer (Evento), etc.
    - A segunda etapa é:
    Quais são as configurações desse consumidor,
    por este motivo foi criado o método consumerConfigurations().
    */
    @Bean
    public ConsumerFactory<String, TransacaoEvento> transactionConsumerFactory() {
        StringDeserializer stringDeserializer = new StringDeserializer();
        JsonDeserializer<TransacaoEvento> jsonDeserializer = new JsonDeserializer<>(TransacaoEvento.class, false);

        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), stringDeserializer, jsonDeserializer);
    }

    //Configuração do listener
    /*
   ConcurrentKafkaListenerContainerFactory,
    Precisa ser cadastrado como serão tratados os eventos recebidos, por isso,
    foi criado o método acima transactionConsumerFactory()!
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransacaoEvento> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransacaoEvento> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(transactionConsumerFactory());

        return factory;
    }

    /*
    Primeiro a gente teve que transcrever as propriedades do application.properties do consumidor para um mapa:
    - public Map<String, Object> consumerConfigurations() (Primeiro método)
    que foi utilizado para definir nosso consumidor e por último foi cadastrado o nosso consumidor no listener!

    Agora é preciso fazer a classe que será o listener que o mesmo saberá como lidar com o Evento, conforme código abaixo:
    @Component
    public class ListenerDeTransacao
     */
}
