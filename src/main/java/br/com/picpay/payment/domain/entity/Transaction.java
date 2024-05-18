package br.com.picpay.payment.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@Table(name = "transactions")
public class Transaction {
  @Id
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long transactionId;

  @Column(name = "payerid")
  Long payerId;

  @Column(name = "payeeid")
  Long payeeId;

  @Column(name = "transactionvalue")
  double value;

  public Transaction(Long payerId, Long payeeId, double value) {
    this.payerId = payerId;
    this.payeeId = payeeId;
    this.value = value;
  }

}
