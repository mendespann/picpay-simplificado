package br.com.picpay.payment.infrastructure.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.usecase.TransferUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transfer")
public class TransactionController {
  TransferUseCase transferUseCase;

  public TransactionController(TransferUseCase transferUseCase) {
    this.transferUseCase = transferUseCase;
  }

  @PostMapping
  public void transfer(@RequestBody Transaction transaction) {
    transferUseCase.transfer(transaction.getPayerId(), transaction.getPayeeId(), transaction.getValue());
  }

}
