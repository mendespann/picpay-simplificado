package br.com.picpay.payment.domain.usecase;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.AuthorizationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
@Slf4j
public class AuthorizationUseCase {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${mock.authorization.url}")
  private String authorizationServiceUrl;

  public void authorizeTransaction(Transaction transaction) throws AuthorizationException {
    try {
      this.restTemplate.getForObject(authorizationServiceUrl, String.class);
      log.info("Transaction authorized, Id: {}", transaction.getTransactionId());
    } catch (RestClientException e) {
      throw new AuthorizationException();
    }
  }
}
