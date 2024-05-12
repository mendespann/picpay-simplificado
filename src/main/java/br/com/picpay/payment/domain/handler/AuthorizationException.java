package br.com.picpay.payment.domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AuthorizationException extends RuntimeException {
  public AuthorizationException() {
    super("Not authorized to perform this operation.");
  }
}