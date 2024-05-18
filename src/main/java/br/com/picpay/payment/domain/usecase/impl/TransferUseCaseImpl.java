package br.com.picpay.payment.domain.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.picpay.payment.domain.entity.Transaction;
import br.com.picpay.payment.domain.entity.User;
import br.com.picpay.payment.domain.enums.UserTypeEnum;
import br.com.picpay.payment.domain.handler.Exception.MerchantNotAllowedException;
import br.com.picpay.payment.domain.handler.Exception.NotEnoughBalanceException;
import br.com.picpay.payment.domain.handler.Exception.UserNotFoundException;
import br.com.picpay.payment.domain.usecase.TransferServiceUseCase;
import br.com.picpay.payment.infrastructure.database.repository.TransactionRepository;
import br.com.picpay.payment.infrastructure.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Data
public class TransferUseCaseImpl implements TransferServiceUseCase{
  private UserRepository userRepository;
  private TransactionRepository transactionRepository;
  private AuthorizationUseCaseImpl authorizationUseCase;
  private NotifyUseCaseImpl notifyUseCase;

  @Transactional
  public void transfer(Long payerId, Long payeeId, double value) {
    userRepository.findAll().forEach(user -> System.out.println(user));

    User payer = userRepository.findById(payerId).orElseThrow(() -> new UserNotFoundException());
    User payee = userRepository.findById(payeeId).orElseThrow(() -> new UserNotFoundException());

    if (payer.getUsertypeenum().equals(UserTypeEnum.MERCHANT)) {
      throw new MerchantNotAllowedException();
    }

    if (payer.getBalance() < value) {
      throw new NotEnoughBalanceException();
    }

    payer.setBalance(payer.getBalance() - value);
    payee.setBalance(payee.getBalance() + value);

    userRepository.save(payer);
    userRepository.save(payee);

    Transaction transaction = new Transaction(payer.getUserId(), payee.getUserId(), value);

    authorizationUseCase.authorizeTransaction(transaction);
    notifyUseCase.notifyUser(transaction);

    transactionRepository.save(transaction);
    log.info("Transaction saved: {}", transaction);
  }

  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }
}
