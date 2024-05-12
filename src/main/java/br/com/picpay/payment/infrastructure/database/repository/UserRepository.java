package br.com.picpay.payment.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.picpay.payment.domain.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  
}
