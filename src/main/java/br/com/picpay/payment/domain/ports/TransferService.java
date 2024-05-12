package br.com.picpay.payment.domain.ports;

import java.util.List;

import br.com.picpay.payment.domain.entity.Transaction;

public interface TransferService {
  public List<Transaction> getAllTransactions();
  public void transfer(Long payerId, Long payeeId, double value);
}
