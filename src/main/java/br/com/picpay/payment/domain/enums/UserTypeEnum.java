package br.com.picpay.payment.domain.enums;

public enum UserTypeEnum {
  REGULAR(1),
  MERCHANT(2);

  private final int value;

  UserTypeEnum(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
