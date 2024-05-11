package br.com.picpay.payment.domain.usecase;


import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.infrastructure.database.repository.TransactionRepository;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;

public class TransferUseCase {
  private UserRepository userRepository;
  private TransactionRepository transactionRepository;

  public TransferUseCase(UserRepository userRepository, TransactionRepository transactionRepository) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
}

  public void transfer(User payer, User payee, double value) {
    if (payer.getBalance() < value) {
      //TODO - Implementar exceção
      //throw new NotEnoughBalanceException();
    }

    if (payer == null || payee == null) {
      //TODO - Implementar exceção
      //throw new UserNotFoundException();
    }

    payer.setBalance(payer.getBalance() - value);
    payee.setBalance(payee.getBalance() + value);

    userRepository.save(payer);
    userRepository.save(payee);

    Transaction transaction = new Transaction(null, payer.getUserId(), payee.getUserId(), value);
    transactionRepository.save(transaction);
  }
}
