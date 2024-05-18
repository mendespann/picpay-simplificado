package br.com.picpay.payment.domain.usecase;

import java.util.List;

import br.com.picpay.payment.domain.entity.Transaction;

public interface TransferServiceUseCase {
  public List<Transaction> getAllTransactions();
  public void transfer(Long payerId, Long payeeId, double value);
}
