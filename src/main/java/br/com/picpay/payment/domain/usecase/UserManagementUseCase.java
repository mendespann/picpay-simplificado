package br.com.picpay.payment.domain.usecase;

import java.util.List;

import br.com.picpay.payment.domain.entity.User;

public interface UserManagementUseCase {
  public User registerUser(User user);

  public List<User> getAllUsers();
}
