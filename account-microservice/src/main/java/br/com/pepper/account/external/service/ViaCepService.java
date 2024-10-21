package br.com.pepper.account.external.service;

import br.com.pepper.account.dto.ViaCepResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCepService {

  private static final Logger logger = LoggerFactory.getLogger(ViaCepService.class);
  private final WebClient viaCepWebClient;

  @Autowired
  public ViaCepService(@Qualifier("viaCepWebClient") WebClient viaCepWebClient) {
    this.viaCepWebClient = viaCepWebClient;
  }

  public ViaCepResponse buscarCEP(String cep) {
    return viaCepWebClient
        .get()
        .uri("/{cep}/json/", cep)
        .retrieve()
        .bodyToMono(ViaCepResponse.class)
        .doOnNext(response -> logger.info("ViaCEP Response Body: {}", response))
        .doOnError(error -> logger.error("Erro ao buscar CEP no ViaCEP: {}", error.getMessage()))
        .block();
  }
}
