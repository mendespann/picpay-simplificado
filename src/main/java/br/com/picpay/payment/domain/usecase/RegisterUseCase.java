package br.com.picpay.payment.domain.usecase;

import org.springframework.stereotype.Service;

import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;

@Service
public class RegisterUseCase {
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
