package br.com.picpay.payment.domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughBalanceException extends RuntimeException {

    public NotEnoughBalanceException() {
        super("Not enough balance");
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
