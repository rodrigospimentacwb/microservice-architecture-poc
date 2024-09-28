# Arquitetura Completa - POC

Este projeto é uma prova de conceito (POC) para demonstrar uma arquitetura completa de microserviços usando Spring Boot 3 e Java 21. O objetivo é mostrar como implementar microserviços em uma arquitetura moderna, incluindo APM, CI/CD com Argo CD, geração de imagem Docker, Istio, e verificação de código no CI.

## Módulos

O projeto está dividido nos seguintes módulos:

1. **Account** - Gerenciamento de contas.
2. **Transactions** - Processamento de transações financeiras.
4. **Statement** - Geração de extratos das contas.

> Esta POC não tem como objetivo a implementação de código e regras de negócio seguindo todas as boas práticas. A implementação será feita de forma simplificada, com o intuito de demonstrar a arquitetura em si.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Docker(Pode-se usar o Postman)**
- **Istio**
- **Argo CD**
- **APM (Application Performance Monitoring)**
- **PostgreSQL**

## Estrutura do Projeto

```
microservice-architeture-poc/
│
├── account/
├── transactions/
└── statement/               
```

## Etapas

## 1. Setup Inicial do Projeto

- [x] Criar um novo repositório no GitHub/GitLab.
- [x] Configurar um projeto Spring Boot 3 usando Java 21(21.0.4-amzn).
- [x] Definir o layout do projeto para múltiplos microserviços (Para a poc, monorepo).
- [x] Adicionar dependências básicas: Spring Web, Spring Data JPA, Spring Actuator, OTEL, etc.

## 2. Desenvolvimento dos Microserviços

- [ ] Implementar os microserviços básicos (ex.: Account, Transaction e Statement) com CRUD simples.
- [ ] Configurar comunicação entre microserviços (REST APIs).
- [ ] Adicionar suporte ao banco de dados (H2 e PostgreSQL) e configurar o repositório JPA.
- [ ] Implementar tratamento de erros.

## 3. Monitoramento (APM)

- [ ] Escolher uma ferramenta de APM (ex.: Prometheus + Grafana, Elastic APM).
- [ ] Adicionar dependências necessárias ao projeto.
- [ ] Configurar métricas no Spring Boot Actuator e integrar com o APM escolhido.

## 4. Containerização com Docker

- [ ] Criar Dockerfiles para cada microserviço.
- [ ] Criar um docker-compose para subir todos os serviços localmente.
- [ ] Testar a execução dos microserviços em containers Docker.

## 5. Configuração do CI/CD com Argo CD

- [ ] Configurar um pipeline de CI/CD (usando GitHub Actions, GitLab CI ou Jenkins).
- [ ] Integrar o pipeline com Argo CD para deploy automatizado.
- [ ] Configurar o Argo CD para monitorar o repositório e implementar os serviços em Kubernetes.
- [ ] Testar a pipeline de CI/CD desde a construção da imagem Docker até a implantação.

## 6. Implementação do Istio para Service Mesh

- [ ] Instalar Istio no cluster Kubernetes.
- [ ] Configurar a entrada do gateway do Istio para os microserviços.
- [ ] Habilitar features do Istio: roteamento de tráfego, balanceamento de carga, circuit breaking.
- [ ] Monitorar a comunicação e o tráfego entre microserviços via Istio.

## 7. Verificação de Código no CI

- [ ] Integrar ferramentas de análise de código (SonarQube, Checkstyle, SpotBugs) no pipeline.
- [ ] Configurar o pipeline para executar verificações de qualidade, testes unitários e cobertura de código.

## 8. Documentação

- [ ] Documentar as etapas de configuração e implantação.
- [ ] Criar guias para cada componente da arquitetura (APM, Docker, Istio, Argo CD).
- [ ] Incluir exemplos de chamadas de API para os microserviços.

## 9. Testes Finais

- [ ] Testar a aplicação end-to-end com todos os componentes em execução.
- [ ] Validar a observabilidade através do APM e Istio.
- [ ] Simular cenários de falhas para testar a resiliência da arquitetura.

## 10. Atualização de DOC

- [ ] Atualizar o README do repositório com instruções detalhadas.

## Como Executar

**Nota:** Instruções detalhadas de execução serão adicionadas conforme o desenvolvimento do projeto.

1. Clone este repositório.
2. Navegue até o diretório do projeto.
3. Execute os comandos necessários para iniciar cada módulo (a ser detalhado).
