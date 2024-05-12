package br.com.picpay.payment.domain.ports;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.AuthorizationException;

public interface AuthorizationService {
  public void authorizeTransaction(Transaction transaction) throws AuthorizationException;
}