package org.example.GlobalSolutionFinal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class GroqAIService {

    private final WebClient webClient;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private static final Logger LOGGER = Logger.getLogger(GroqAIService.class.getName());

    public GroqAIService(WebClient groqWebClient) {
        this.webClient = groqWebClient;
    }

    public Mono<String> gerarResposta(List<Map<String, String>> messages) {
        Map<String, Object> payload = Map.of(
                "model", "meta-llama/llama-4-scout-17b-16e-instruct",
                "messages", messages,
                "temperature", 0.7
        );

        return webClient.post()
                .uri("/openai/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        if (message != null) {
                            String content = (String) message.get("content");
                            return content != null ? content.trim() : "Resposta vazia da IA.";
                        }
                    }
                    return "Nenhuma resposta gerada.";
                })
                .doOnError(e -> LOGGER.severe("Erro na chamada Groq AI: " + e.getMessage()))
                .onErrorResume(WebClientResponseException.class, e -> {
                    LOGGER.severe("Erro HTTP da API Groq: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
                    return Mono.just("Erro na API Groq: " + e.getRawStatusCode());
                })
                .onErrorResume(e -> Mono.just("Erro inesperado: " + e.getMessage()));
    }
}
