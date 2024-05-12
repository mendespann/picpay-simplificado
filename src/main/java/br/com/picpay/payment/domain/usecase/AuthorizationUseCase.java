package br.com.picpay.payment.domain.usecase;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.AuthorizationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import br.com.picpay.payment.domain.ports.AuthorizationService;

@Service
@Slf4j
@Data
public class AuthorizationUseCase implements AuthorizationService {
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
