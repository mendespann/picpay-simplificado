package br.com.picpay.payment.infrastructure.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.usecase.TransferServiceUseCase;
import br.com.picpay.payment.domain.usecase.UserManagementUseCase;
import br.com.picpay.payment.domain.usecase.impl.TransferUseCaseImpl;
import br.com.picpay.payment.domain.usecase.impl.UserManagementUseCaseImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
@OpenAPIDefinition(info = @Info(title = "Desafio Backend Picpay Simplificado", version = "v1", description = "Implementação de um sistema de pagamentos simplificado."))
public class TransactionController {
  TransferServiceUseCase transferUseCase;
  UserManagementUseCase userManagementUseCase;

  public TransactionController(TransferUseCaseImpl transferUseCase, UserManagementUseCaseImpl userManagementUseCase) {
    this.transferUseCase = transferUseCase;
    this.userManagementUseCase = userManagementUseCase;
  }

  @Operation(summary = "Retorna o histórico de transações")
  @GetMapping("/history")
  public List<Transaction> getTransactionHistory() {
    return transferUseCase.getAllTransactions();
  }

  @Operation(summary = "Cria uma nova transferência")
  @PostMapping("/transfer")
  public void transfer(Transaction transaction) {
    transferUseCase.transfer(transaction.getPayerId(), transaction.getPayeeId(), transaction.getValue());
  }

  @Operation(summary = "Registra um novo usuário")
  @GetMapping("/users")
  public List<User> getUsers() {
    return userManagementUseCase.getAllUsers();
  }

}
