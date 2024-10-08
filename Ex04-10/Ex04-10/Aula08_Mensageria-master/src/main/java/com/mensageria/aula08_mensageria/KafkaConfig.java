package com.mensageria.aula08_mensageria;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de Configuração do Kafka para o produtor
 * Configurações para permitir o envio em lotes
 */
public class KafkaConfig {
     /**
      * Metodo que define o Factory para criar produtores Kafka
      * Configurar o Kafka para trabalhar com StringSerializer para as chaves
      * e valores, habilitando o envio de mensagens em lote, o caminho é ajustado
      * em batch.size
      * O tempo de espera está em linger ms
      * @return Um ProducerFactory configurado para uso com o KafkaTemplaate
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers", "localhost:9092");
        configProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //Configurações de Batches
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); //16 Kb de tamanho para o lote
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 10); //Espera de 10 ms para tentar agrupar mensagens
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); //32 Mb de buffer
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Criação do KafkaTemplate, que facilitará o envio de mensagens para o Kafka
     * O KafkaTemplate é o principal componente utilizado para produzir mensagens
     * Ele encapsula a lógica de configuração do produtor.
     * @return KafkaTemplate para enviar mensagens
     */

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }



}
