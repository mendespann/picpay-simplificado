package br.com.picpay.payment.infrastructure.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.enums.UserTypeEnum;
import br.com.picpay.payment.domain.usecase.RegisterUseCase;

public class DatabaseConfig {
@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(RegisterUseCase registerUseCase) {
        return args -> {
            registerUseCase.registerUser(new User(null, "Pamela", "123.456.789-00", "pamela@hotmail.com", "123", 100.0 ,UserTypeEnum.REGULAR));
            registerUseCase.registerUser(new User(null, "Crystal", "123.456.783-00", "crystal@hotmail.com", "123", 200.0, UserTypeEnum.MERCHANT));
        };
    }
}
}
