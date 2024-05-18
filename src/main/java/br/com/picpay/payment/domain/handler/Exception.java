package br.com.picpay.payment.domain.handler;

public class Exception {
  public static class MerchantNotAllowedException extends RuntimeException {
    public MerchantNotAllowedException() {
      super("Merchant not allowed to transfer money.");
    }
  }

  public static class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException() {
      super("Not enough balance");
    }
  }

  public static class FailedNotificationException extends RuntimeException {
    public FailedNotificationException() {
      super("Failed to notify the transaction.");
    }
  }

  public static class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
      super("Not authorized to perform this operation.");
    }
  }

  public static class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
      super("User not found");
    }

  }
}
