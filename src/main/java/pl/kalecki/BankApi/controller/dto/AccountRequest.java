package pl.kalecki.BankApi.controller.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class AccountRequest {

    private final String currency;
    private final double balance;
    private final long userId;
}
