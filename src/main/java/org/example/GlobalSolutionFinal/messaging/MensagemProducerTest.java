package org.example.GlobalSolutionFinal.messaging;

import org.example.GlobalSolutionFinal.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class MensagemProducerTest {

    // Subclasse para mock manual do RabbitTemplate com controle de chamadas
    static class FakeRabbitTemplate extends RabbitTemplate {
        public boolean convertAndSendCalled = false;
        public String exchangeUsed = null;
        public String routingKeyUsed = null;
        public Object mensagemEnviada = null;

        @Override
        public void convertAndSend(String exchange, String routingKey, Object message) {
            convertAndSendCalled = true;
            exchangeUsed = exchange;
            routingKeyUsed = routingKey;
            mensagemEnviada = message;
            System.out.println("convertAndSend chamado com: " + exchange + ", " + routingKey + ", " + message);
        }
    }

    public static void main(String[] args) {
        // Usa a subclasse explícita
        FakeRabbitTemplate fakeRabbitTemplate = new FakeRabbitTemplate();

        MensagemProducer produtor = new MensagemProducer(fakeRabbitTemplate);

        String mensagem = "Teste Unitário Manual";
        produtor.enviarMensagem(mensagem);

        if (!fakeRabbitTemplate.convertAndSendCalled) {
            throw new RuntimeException("convertAndSend não foi chamado!");
        }
        if (!RabbitMQConfig.EXCHANGE_NAME.equals(fakeRabbitTemplate.exchangeUsed)) {
            throw new RuntimeException("Exchange incorreto!");
        }
        if (!RabbitMQConfig.ROUTING_KEY.equals(fakeRabbitTemplate.routingKeyUsed)) {
            throw new RuntimeException("RoutingKey incorreto!");
        }
        if (!mensagem.equals(fakeRabbitTemplate.mensagemEnviada)) {
            throw new RuntimeException("Mensagem incorreta!");
        }
        System.out.println("Teste unitário manual passou!");
    }
}
