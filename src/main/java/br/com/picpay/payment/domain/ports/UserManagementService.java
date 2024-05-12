package br.com.picpay.payment.domain.ports;

import java.util.List;

import br.com.picpay.payment.domain.entity.User;

public interface UserManagementService {
  public User registerUser(User user);

  public List<User> getAllUsers();
}
