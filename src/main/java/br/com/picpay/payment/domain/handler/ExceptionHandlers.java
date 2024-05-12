package br.com.picpay.payment.domain.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {

  @ExceptionHandler(FailedNotificationException.class)
  public ResponseEntity<String> handleFailedNotificationException() {
    return ResponseEntity.status(500).body("Failed to notify the transaction.");
  }

  @ExceptionHandler(MerchantNotAllowedException.class)
  public ResponseEntity<String> handleMerchantNotAllowedException() {
    return ResponseEntity.status(400).body("Merchant not allowed to transfer money.");
  }

  @ExceptionHandler(NotEnoughBalanceException.class)
  public ResponseEntity<String> handleNotEnoughBalanceException() {
    return ResponseEntity.status(400).body("Not enough balance to transfer money.");
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException() {
    return ResponseEntity.status(404).body("User not found.");
  }

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<String> handleAuthorizationException() {
    return ResponseEntity.status(401).body("Authorization failed.");
  }
}
