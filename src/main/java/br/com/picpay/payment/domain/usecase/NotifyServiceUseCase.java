package br.com.picpay.payment.domain.usecase;

import br.com.picpay.payment.domain.entity.Transaction;

public interface NotifyServiceUseCase {
  public void notifyUser(Transaction transaction);
}
