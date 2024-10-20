package br.com.pepper.account.service;

import br.com.pepper.account.dto.AccountResponse;
import br.com.pepper.account.exception.CustomNotFoundException;
import br.com.pepper.account.model.Account;
import br.com.pepper.account.repository.AccountRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository repository;

  public AccountService(final AccountRepository repository) {
    this.repository = repository;
  }

  public AccountResponse getAccount(final Long id) {
    Optional<Account> account = repository.findById(id);
    return modelToDto(
        account.orElseThrow(() -> new CustomNotFoundException("Account " + id + " not found")));
  }

  private AccountResponse modelToDto(final Account account) {
    return AccountResponse.from(account);
  }
}
