package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.service.GroqAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/groq")
public class GroqAIController {

    @Autowired
    private GroqAIService groqAIService;

    @PostMapping("/chat")
    public Mono<String> chat(@RequestBody Map<String, List<Map<String, String>>> payload) {
        List<Map<String, String>> messages = payload.get("messages");
        if (messages == null || messages.isEmpty()) {
            return Mono.just("Erro: messages não pode ser vazio");
        }
        return groqAIService.gerarResposta(messages);
    }
}
