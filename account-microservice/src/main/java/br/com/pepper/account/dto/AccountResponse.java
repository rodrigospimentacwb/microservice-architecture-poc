package br.com.pepper.account.dto;

import br.com.pepper.account.model.Account;
import java.math.BigDecimal;

public record AccountResponse(Long id, String name, String email, BigDecimal balance) {

  public static AccountResponse from(final Account account) {
    return new AccountResponse(
        account.getId(), account.getName(), account.getEmail(), account.getBalance());
  }
}
