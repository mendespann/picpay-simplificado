package br.com.picpay.payment.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.handler.AuthorizationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorizationUseCaseTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthorizationUseCase authorizationUseCase;
    
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(authorizationUseCase, "authorizationServiceUrl", "http://example.com");
    }
    @Test
    public void testAuthorizeTransactionSuccess() {
        Transaction transaction = new Transaction(); // Crie uma transação de teste aqui
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("Success");

        authorizationUseCase.authorizeTransaction(transaction);

        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

    @Test
    public void testAuthorizeTransactionFailure() {
        Transaction transaction = new Transaction(); // Crie uma transação de teste aqui
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(RestClientException.class);

        assertThrows(AuthorizationException.class, () -> {
            authorizationUseCase.authorizeTransaction(transaction);
        });

        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

}