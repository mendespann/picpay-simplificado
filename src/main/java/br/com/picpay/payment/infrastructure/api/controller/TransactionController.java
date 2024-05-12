package br.com.picpay.payment.infrastructure.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.ports.TransferService;
import br.com.picpay.payment.domain.ports.UserManagementService;
import br.com.picpay.payment.domain.usecase.UserManagementUseCase;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import br.com.picpay.payment.domain.usecase.TransferUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
@OpenAPIDefinition(info = @Info(title = "Desafio Backend Picpay Simplificado", version = "v1", description = "Implementação de um sistema de pagamentos simplificado."))
public class TransactionController {
  TransferService transferUseCase;
  UserManagementService userManagementUseCase;

  public TransactionController(TransferUseCase transferUseCase, UserManagementUseCase userManagementUseCase) {
    this.transferUseCase = transferUseCase;
    this.userManagementUseCase = userManagementUseCase;
  }

  @PostMapping("/transfer")
  public void transfer(@RequestBody Transaction transaction) {
    transferUseCase.transfer(transaction.getPayerId(), transaction.getPayeeId(), transaction.getValue());
  }

  @GetMapping("/history")
  public List<Transaction> getTransactionHistory() {
    return transferUseCase.getAllTransactions();
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userManagementUseCase.getAllUsers();
  }

}
