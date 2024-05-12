package br.com.picpay.payment.domain.ports;

import br.com.picpay.payment.domain.entity.Transaction;

public interface NotifyService {
  public void notifyUser(Transaction transaction);
}
