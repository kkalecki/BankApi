package pl.kalecki.BankApi.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private final long id;
    private final double price;
    private final long fromAccountId;
    private final long toAccountId;
}
