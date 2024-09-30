FROM amazoncorretto:21

WORKDIR /app

# Copiar jar da aplicaco para a imagem
COPY target/account-microservice-*.jar app.jar

# Baixando o agente Java do OpenTelemetry (Pode-se configurar para baixar do S3)
RUN wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.30.0/opentelemetry-javaagent.jar

# Variáveis de ambiente para o OpenTelemetry
ENV OTEL_SERVICE_NAME=account-microservice
ENV OTEL_TRACES_SAMPLER=always_on
ENV OTEL_TRACES_EXPORTER=otlp
ENV OTEL_METRICS_EXPORTER=otlp
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://otel-collector:4317

# ENV OTEL_TRACES_SAMPLER=parentbased_traceidratio --SE for usar tracert parcial
# ENV OTEL_TRACES_SAMPLER_ARG=0.8 -- 80% dos tracerts apenas sao coletados

# Porta da aplicação exposta
EXPOSE 8080

# Execute a aplicação com o agente OpenTelemetry
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "/app/app.jar"]
