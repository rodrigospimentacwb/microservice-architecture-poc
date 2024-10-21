package br.com.pepper.account.config;

import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.Logbook;

@Configuration
public class LogbookConfig {

  @Bean
  public Logbook logbook() {
    return Logbook.builder()
        .condition(excludeHealthEndpoint()) // Exemplo: excluindo endpoints de saúde
        .build();
  }

  private Predicate<HttpRequest> excludeHealthEndpoint() {
    return request -> !request.getPath().startsWith("/actuator/health");
  }
}
