package com.mensageria.aula08_mensageria;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por consumir mensagens em lote do Kafka
 *
 * O consumidor é configurado para processar várias mensagens de uma vez,
 * utilizando o processamento em lote oferecido pelo Kafka.
 */
@Service
public class KafkaBatchConsumer {
    /**
     * Metodo que consome mensagens em lote do tópico informado.
     * O KafkaListener escuta o tópico configurado e recebe as mensagens em lote.
     * O parâmetro List<String> indica que o metodo é capaz de receber
     * múltiplas mensagens em uma única invocação.
     *
     * @param messages Um lote de mensagens recebidas do tópico
     */
    @KafkaListener(topics = "batch-topico", groupId = "batch-grupo",containerFactory = "kafkaListenerContainerFactory")
    public void consumeBatch(List<String> messages) {
        System.err.println("Recebido: " + messages);
        processBatch(messages);
    }

    /**
     * Metodo que processa cada mensagem individualmente de um lote.
     * Fast Delegate: Este metodo é chamado para processar cada mensagem
     * que foi recebida em lote.
     * Isso ajuda a modularizar o código, facilitando o entendimento.
     *
     * @param mensagens lista de mensagens recebidas do lote
     */
    private void processBatch(List<String> mensagens) {
        mensagens.forEach(
            mensagem -> {
                System.err.println("Processando: " + mensagem);
            }
        );
    }
}
