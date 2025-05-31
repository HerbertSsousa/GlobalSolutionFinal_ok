package org.example.GlobalSolutionFinal.messaging;

import org.example.GlobalSolutionFinal.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class MensagemConsumer {

    // Fila simples para guardar mensagens recebidas (para teste)
    private final BlockingQueue<String> mensagensRecebidas = new LinkedBlockingQueue<>();

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
        mensagensRecebidas.offer(mensagem);
    }

    // Método para teste pegar a próxima mensagem recebida, ou null se não tiver
    public String obterMensagemRecebida(long timeoutMillis) throws InterruptedException {
        return mensagensRecebidas.poll(timeoutMillis, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}
