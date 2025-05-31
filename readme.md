# Gs Evacuação - Sistema Inteligente de Evacuação em Situações de Risco Extremo

---

## Faculdade de Informática e Administração Paulista

**Alunos:**
- Herbert Santos de Sousa: RM553227
- João Pedro Pereira: RM553698
- Enzo Franco Rocha: RM553643

---

## Descrição do Projeto

O **Gs Evacuação** é uma solução web inovadora que utiliza tecnologia e inteligência artificial para mitigar os impactos dos eventos extremos na população. O sistema oferece monitoramento de riscos, emissão de alertas, cadastro de rotas seguras e recomendação automatizada de evacuação através de IA.

### Funcionalidades principais:
- Cadastro de usuários com autenticação OAuth2 (Google e GitHub) ou via formulário local.
- CRUD completo de usuários com validação.
- Sistema de recomendações via IA usando Groq (modelo Llama3).
- Integração com RabbitMQ para mensageria de alertas (producer e consumer).
- Internacionalização em Português e Inglês.
- Testes unitários e de integração para garantir qualidade.

---

## Tecnologias Utilizadas

- Spring Boot 3 (Spring MVC, Spring Security, Spring AI)
- Thymeleaf (Templates dinâmicos)
- OAuth2 (Google e GitHub)
- RabbitMQ (Producer e Consumer)
- Oracle Database
- WebClient + Groq API (IA)
- JPA (Hibernate)
- JUnit (Testes unitários e integração)
- Internacionalização via `messages.properties`
- CORS configurado para integração

---

## Arquitetura do Sistema

- Camada Web: Controllers MVC com Thymeleaf para renderização dinâmica
- Camada de Serviço: Lógica de negócio separada
- Camada de Persistência: JPA Repositories para acesso ao banco Oracle
- Configurações específicas:
    - Segurança (SecurityConfig)
    - Mensageria (RabbitMQConfig)
    - Internacionalização (InternationalizationConfig)
    - CORS (CorsConfig)
    - Configuração da API Groq (GroqConfig)
- Integração com Groq AI via WebClient
- Producer e Consumer para RabbitMQ

---

## Instruções para Execução

1. Banco de Dados Oracle
    - URL JDBC: `jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL`
    - Usuário e senha conforme arquivo `application.properties`.

2. RabbitMQ
    - Host local na porta `15672`
    - Usuário: `guest`
    - Senha: `guest`

3. Groq AI
    - API Key configurada no `application.properties`
    - Base URL: `https://api.groq.com`

4. Rodar o projeto Spring Boot na porta `8081`.

5. Login
    - Via OAuth2 (Google e GitHub) ou conta criada manualmente pelo formulário.

---

## Testes Realizados

- Testes unitários implementados em `MensagemProducerTest`
- Testes de integração com RabbitMQ via `MensagemRabbitIntegrationTest`

---

## Links Importantes

- Repositório GitHub: [https://github.com/seu-usuario/seu-projeto](https://github.com/HerbertSsousa/GlobalSolutionFinal_ok)
- Vídeo Demonstração: https://youtu.be/mUyWfbqVL0w?si=B9vuy_vgvxr0wH5B
- Vídeo Pitch: https://youtu.be/xJd8OjFIsug

---

## Conclusão

O projeto atende a todos os requisitos técnicos solicitados, integrando inteligência artificial com Groq (Llama3), autenticação segura via OAuth2, gestão completa de usuários, monitoramento de riscos e mensageria eficiente com RabbitMQ. A solução oferece inovação e robustez para enfrentar situações de risco extremo, auxiliando a população na evacuação rápida e segura.

---

## Contato

Para dúvidas ou contribuições, entre em contato com a equipe pelo repositório ou via e-mail (informar, se desejar).

