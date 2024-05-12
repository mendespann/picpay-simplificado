package br.com.picpay.payment.domain.entity;

import br.com.picpay.payment.domain.enums.UserTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long userId;

  @Column(name = "fullname")
  String fullname;

  @Column(name = "cpf", unique = true)
  String cpf;

  @Column(name = "email", unique = true)
  String email;

  @Column(name = "password")
  String password;

  @Column(name = "balance")
  double balance;

  @Column(name = "usertype")
  @Enumerated(EnumType.STRING)
  UserTypeEnum usertype;
}
