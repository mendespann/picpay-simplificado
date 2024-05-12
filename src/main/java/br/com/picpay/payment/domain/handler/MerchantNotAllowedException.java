package br.com.picpay.payment.domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MerchantNotAllowedException extends RuntimeException{
  public MerchantNotAllowedException() {
    super("Merchant not allowed to transfer money.");
  }
}
