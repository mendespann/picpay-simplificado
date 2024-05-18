package br.com.picpay.payment.domain.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.Exception.AuthorizationException;
import br.com.picpay.payment.domain.usecase.AuthorizationUseCase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

@Service
@Slf4j
@Data
public class AuthorizationUseCaseImpl implements AuthorizationUseCase {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${mock.authorization.url}")
  private String authorizationServiceUrl;

  public void authorizeTransaction(Transaction transaction) throws AuthorizationException {
    try {
      ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(authorizationServiceUrl, String.class);

      if (responseEntity.getStatusCode().value() == 200) {
        log.info("Transaction authorized.");
      } else {
        throw new AuthorizationException();
      }
    } catch (RestClientException e) {
      throw new AuthorizationException();
    }
  }
}
