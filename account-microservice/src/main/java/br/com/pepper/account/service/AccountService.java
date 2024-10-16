package br.com.pepper.account.service;

import br.com.pepper.account.dto.AccountResponse;
import br.com.pepper.account.model.Account;
import br.com.pepper.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository repository;

  public AccountService(final AccountRepository repository) {
    this.repository = repository;
  }

  public AccountResponse getAccount(final Long id) {
    return modelToDto(repository.findById(id).orElseThrow(RuntimeException::new));
  }

  private AccountResponse modelToDto(final Account account) {
    return AccountResponse.from(account);
  }
}
