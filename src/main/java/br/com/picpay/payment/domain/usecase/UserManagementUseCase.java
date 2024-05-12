package br.com.picpay.payment.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.ports.UserManagementService;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;


@Service
public class UserManagementUseCase implements UserManagementService{
    private UserRepository userRepository;

    public UserManagementUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
