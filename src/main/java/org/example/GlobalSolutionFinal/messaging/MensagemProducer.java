package org.example.GlobalSolutionFinal.messaging;

import org.example.GlobalSolutionFinal.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MensagemProducer {

    private final RabbitTemplate rabbitTemplate;

    public MensagemProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                mensagem);
    }
}
