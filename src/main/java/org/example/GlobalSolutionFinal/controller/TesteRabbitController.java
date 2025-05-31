package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.messaging.MensagemProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste-rabbit")
public class TesteRabbitController {

    private final MensagemProducer produtor;

    public TesteRabbitController(MensagemProducer produtor) {
        this.produtor = produtor;
    }

    @GetMapping("/enviar")
    public String enviarMensagemTeste(@RequestParam String msg) {
        produtor.enviarMensagem(msg);
        return "Mensagem enviada: " + msg;
    }
}
