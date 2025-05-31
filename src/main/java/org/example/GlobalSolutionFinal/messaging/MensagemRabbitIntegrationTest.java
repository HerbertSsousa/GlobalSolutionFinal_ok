package org.example.GlobalSolutionFinal.messaging;

import org.example.GlobalSolutionFinal.config.RabbitMQConfig;

public class MensagemRabbitIntegrationTest {

    public static void main(String[] args) throws InterruptedException {
        var contexto = new org.springframework.context.annotation.AnnotationConfigApplicationContext(
                org.example.GlobalSolutionFinal.config.RabbitMQConfig.class,
                MensagemProducer.class,
                MensagemConsumer.class
        );

        var producer = contexto.getBean(MensagemProducer.class);
        var consumer = contexto.getBean(MensagemConsumer.class);

        String mensagemTeste = "Mensagem de teste integração simples";

        producer.enviarMensagem(mensagemTeste);

        System.out.println("Mensagem enviada: " + mensagemTeste);

        // Espera simples para consumir (3s)
        String msgRecebida = consumer.obterMensagemRecebida(3000);

        if (msgRecebida == null) {
            System.err.println("Erro: mensagem não recebida");
            System.exit(1);
        }

        if (!mensagemTeste.equals(msgRecebida)) {
            System.err.println("Erro: mensagem recebida difere da enviada");
            System.exit(1);
        }

        System.out.println("Mensagem recebida com sucesso: " + msgRecebida);
        System.out.println("Teste integração manual passou!");
        contexto.close();
    }
}
