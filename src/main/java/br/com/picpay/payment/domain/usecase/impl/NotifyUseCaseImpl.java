package br.com.picpay.payment.domain.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.Exception.FailedNotificationException;
import br.com.picpay.payment.domain.usecase.NotifyServiceUseCase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class NotifyUseCaseImpl implements NotifyServiceUseCase {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${mock.notification.url}")
  private String notificationServiceUrl;

  public void notifyUser(Transaction transaction) {
    log.info("Checking notification service...");

    ResponseEntity<String> responseEntity = restTemplate.getForEntity(notificationServiceUrl, String.class);

    if (responseEntity.getStatusCode().value() == 200) {
      log.info("Response from notification service: " + transaction.toString());
    } else {
      throw new FailedNotificationException();
    }
  }
}
