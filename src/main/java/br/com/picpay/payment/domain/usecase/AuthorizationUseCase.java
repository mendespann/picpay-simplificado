package br.com.picpay.payment.domain.usecase;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.Exception.AuthorizationException;

public interface AuthorizationUseCase {
  public void authorizeTransaction(Transaction transaction) throws AuthorizationException;
}