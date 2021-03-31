package pl.kalecki.BankApi.controller.dto;

import lombok.Builder;
import lombok.Data;
import pl.kalecki.BankApi.repository.entity.Transaction;

import javax.persistence.Column;
import java.util.List;

@Data
@Builder
public class AccountResponse {

    private final long id;
    private final String currency;
    private final double balance;
    private final long userId;
    List<Transaction> transactionsFrom;
    List<Transaction> transactionsTo;
}
