package br.com.pepper.account.service;

import br.com.pepper.account.dto.AccountResponse;
import br.com.pepper.account.exception.CustomNotFoundException;
import br.com.pepper.account.external.service.ViaCepService;
import br.com.pepper.account.model.Account;
import br.com.pepper.account.repository.AccountRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
  private final AccountRepository repository;
  private final ViaCepService viaCepService;

  public AccountService(final AccountRepository repository, final ViaCepService viaCepService) {
    this.repository = repository;
    this.viaCepService = viaCepService;
  }

  public AccountResponse getAccount(final Long id) {
    Optional<Account> account = repository.findById(id);

    // TODO - Simulate external call here
    callBuscaCEP();

    return modelToDto(
        account.orElseThrow(() -> new CustomNotFoundException("Account " + id + " not found")));
  }

  private AccountResponse modelToDto(final Account account) {
    return AccountResponse.from(account);
  }

  private void callBuscaCEP() {
    logger.info(String.valueOf(viaCepService.buscarCEP("01001000")));
  }
}
