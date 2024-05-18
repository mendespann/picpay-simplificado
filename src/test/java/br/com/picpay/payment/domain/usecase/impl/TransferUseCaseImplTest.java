package br.com.picpay.payment.domain.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.enums.UserTypeEnum;
import br.com.picpay.payment.domain.handler.Exception.NotEnoughBalanceException;
import br.com.picpay.payment.domain.handler.Exception.UserNotFoundException;
import br.com.picpay.payment.infrastructure.database.repository.TransactionRepository;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;

public class TransferUseCaseImplTest {

    @InjectMocks
    private TransferUseCaseImpl transferUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthorizationUseCaseImpl authorizationUseCase;

    @Mock
    private NotifyUseCaseImpl notifyUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransfer() {
        User payer = new User();
        payer.setUserId(1L);
        payer.setBalance(1000.0);
        payer.setUsertypeenum(UserTypeEnum.REGULAR);

        User payee = new User();
        payee.setUserId(2L);
        payee.setBalance(500.0);

        when(userRepository.findById(1L)).thenReturn(Optional.of(payer));
        when(userRepository.findById(2L)).thenReturn(Optional.of(payee));

        transferUseCase.transfer(1L, 2L, 200.0);

        verify(userRepository, times(2)).save(any(User.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(authorizationUseCase, times(1)).authorizeTransaction(any(Transaction.class));
        verify(notifyUseCase, times(1)).notifyUser(any(Transaction.class));
    }



    @Test
    public void testTransfer_NotEnoughBalance() {
        User payer = new User(1L, "Payer", "123.456.789-00", "payer@hotmail.com", "123", 50.0, UserTypeEnum.REGULAR);
        User payee = new User(2L, "Payee", "123.456.789-01", "payee@hotmail.com", "123", 100.0, UserTypeEnum.MERCHANT);

        when(userRepository.findById(1L)).thenReturn(Optional.of(payer));
        when(userRepository.findById(2L)).thenReturn(Optional.of(payee));

        assertThrows(NotEnoughBalanceException.class, () -> {
            transferUseCase.transfer(1L, 2L, 100.0);
        });
    }

    @Test
    public void testTransfer_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            transferUseCase.transfer(1L, 2L, 50.0);
        });
    }
}