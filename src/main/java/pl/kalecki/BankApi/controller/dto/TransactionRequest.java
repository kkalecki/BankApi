package pl.kalecki.BankApi.controller.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;
@Data
public class TransactionRequest {

    private final double price;
    private final long fromAccountId;
    private final long toAccountId;
}
