package com.mensageria.aula08_mensageria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  Serviço responsável por enviar mensagens ao Kafka em lote
 *  Está sendo utilizado o KafkaTemplate, que são agrupadas em lotes
 *  de acordo com as configurações definidas.
 */
@Service
public class KafkaBatchProducer {

    //KafkaTemplate injeta a capacidade de produzir mensagem para o Kafka
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    //Nome do tópico onde as mensagens serão enviadas
    private final String TOPIC = "batch-topico";
    /**
     * Envia uma série de mensagens para o Kafka de forma agrupada
     * Este metodo envia 20 mensagens de batch-size ao tópico criado
     * Seguirá as configurações de batch-size e linger.ms. E serão enviadas em lote
     */

    private List<String> createMessages(String prioridade, String baseMessage, int count) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String message = String.format("Mensagem %s %d: %s", prioridade, i, baseMessage);
            messages.add(message);
        }
        sendMessages(messages);
        return messages;
    }

    private void sendMessages(List<String> messages) {
        for (String message : messages) {
            kafkaTemplate.send(TOPIC, message);
            System.out.println("Enviado: " + message);
        }
        System.out.println("Batch enviado: " + messages);
    }

    public void sendMessagesInBatchAlta(String message) {
        createMessages("Alta", message, 1);
    }

    public void sendMessagesInBatchMedia(String message) {
        createMessages("Média", message, 15);
    }

    public void sendMessagesInBatchBaixa(String message) {
        createMessages("Baixa", message, 20);
    }
}
