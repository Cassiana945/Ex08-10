package com.mensageria.aula08_mensageria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 *
 * Esta classe é o ponto de entrada da aplicação e é responsável por iniciar o envio de mensagens
 * em lote logo após a inicialização.
 */
@SpringBootApplication
public class Aula08MensageriaApplication implements CommandLineRunner {
    @Autowired
    private KafkaBatchProducer kafkaBatchProducer;

    /**
     * Metodo principal que inicia a aplicação Spring Boot.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        SpringApplication.run(Aula08MensageriaApplication.class, args);
    }

    /**
     * Metodo que é executado após a inicialização da aplicação.
     *
     * Aqui chamamos o metodo `sendMessagesInBatch()` do `KafkaBatchProducer` para enviar
     * as mensagens ao Kafka logo após a inicialização.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     * @throws Exception Se houver um erro durante a execução.
     */
    @Override
    public void run(String... args) throws Exception {
        // Definindo mensagens padrão para cada prioridade
        String altaMessage = "Mensagem importante";
        String mediaMessage = "Mensagem informativa";
        String baixaMessage = "Mensagem de rotina";

        // Chamando o metodo que envia mensagens em lote com mensagens específicas
        kafkaBatchProducer.sendMessagesInBatchAlta(altaMessage);
        kafkaBatchProducer.sendMessagesInBatchMedia(mediaMessage);
        kafkaBatchProducer.sendMessagesInBatchBaixa(baixaMessage);
    }

}
