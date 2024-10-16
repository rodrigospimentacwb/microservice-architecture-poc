package br.com.pepper.account.controller;

import br.com.pepper.account.dto.AccountResponse;
import br.com.pepper.account.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts/v1")
public class AccountController {
  private final AccountService service;

  public AccountController(final AccountService accountService) {
    this.service = accountService;
  }

  @GetMapping("/{id}")
  public AccountResponse getAccount(@PathVariable final Long id) {
    return service.getAccount(id);
  }
}
