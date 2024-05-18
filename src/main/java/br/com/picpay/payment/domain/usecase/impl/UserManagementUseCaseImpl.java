package br.com.picpay.payment.domain.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.usecase.UserManagementUseCase;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;


@Service
public class UserManagementUseCaseImpl implements UserManagementUseCase{
    private UserRepository userRepository;

    public UserManagementUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
