package br.com.picpay.payment.domain.usecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserManagementUseCaseTest {

    @InjectMocks
    private UserManagementUseCase userManagementUseCase;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userManagementUseCase.registerUser(user);

        assertEquals(user.getUserId(), result.getUserId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUserId(1L);

        User user2 = new User();
        user2.setUserId(2L);

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userManagementUseCase.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }
}