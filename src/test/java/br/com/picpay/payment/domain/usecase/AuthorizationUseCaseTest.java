package br.com.picpay.payment.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
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
    public void testAuthorizeTransactionSuccess() throws AuthorizationException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        AuthorizationUseCase authorizationUseCase = new AuthorizationUseCase();
        authorizationUseCase.setRestTemplate(restTemplate);
        authorizationUseCase.setAuthorizationServiceUrl("http://mock.url");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenAnswer(invocation -> new ResponseEntity<>("Success", HttpStatus.OK));
        Transaction transaction = new Transaction(); 

        authorizationUseCase.authorizeTransaction(transaction);
    }

    @Test
    public void testAuthorizeTransactionFailure() throws AuthorizationException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        AuthorizationUseCase authorizationUseCase = new AuthorizationUseCase();
        authorizationUseCase.setRestTemplate(restTemplate);
        authorizationUseCase.setAuthorizationServiceUrl("http://mock.url");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenAnswer(invocation -> new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR));
        Transaction transaction = new Transaction(); 

        assertThrows(AuthorizationException.class, () -> {
            authorizationUseCase.authorizeTransaction(transaction);
        });
    }

 }

