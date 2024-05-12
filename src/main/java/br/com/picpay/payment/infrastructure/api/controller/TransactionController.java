package br.com.picpay.payment.infrastructure.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.usecase.UserManagementUseCase;
import br.com.picpay.payment.domain.usecase.TransferUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping
public class TransactionController {
  TransferUseCase transferUseCase;
  UserManagementUseCase userManagementUseCase;

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
