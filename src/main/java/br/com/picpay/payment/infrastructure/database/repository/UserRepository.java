package br.com.picpay.payment.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.picpay.payment.domain.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
  
}
