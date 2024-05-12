package br.com.picpay.payment.domain.usecase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.FailedNotificationException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotifyUseCaseTest {

    @Test
    public void testNotifyUser_Success() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        NotifyUseCase notifyUseCase = new NotifyUseCase();
        notifyUseCase.setRestTemplate(restTemplate);
        notifyUseCase.setNotificationServiceUrl("http://mock.url");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenAnswer(invocation -> new ResponseEntity<>("Success", HttpStatus.OK));
        Transaction transaction = new Transaction();

        notifyUseCase.notifyUser(transaction);
    }

    @Test
    public void testNotifyUser_Failure() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        NotifyUseCase notifyUseCase = new NotifyUseCase();
        notifyUseCase.setRestTemplate(restTemplate);
        notifyUseCase.setNotificationServiceUrl("http://mock.url");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenAnswer(invocation -> new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR));
        Transaction transaction = new Transaction();

        assertThrows(FailedNotificationException.class, () -> {
            notifyUseCase.notifyUser(transaction);
        });
    }
}