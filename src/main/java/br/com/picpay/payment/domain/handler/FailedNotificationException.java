package br.com.picpay.payment.domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedNotificationException extends RuntimeException{
  public FailedNotificationException() {
    super("Failed to notify the transaction.");
  }
}
