package br.com.pepper.account.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private BigDecimal balance;

  // Construtor padrão
  public Account() {}

  // Construtor com parâmetros
  public Account(final String name, final String email, final BigDecimal balance) {
    this.name = name;
    this.email = email;
    this.balance = balance;
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(final BigDecimal balance) {
    this.balance = balance;
  }
}
