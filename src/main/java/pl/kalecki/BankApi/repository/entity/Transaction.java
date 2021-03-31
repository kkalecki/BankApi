package pl.kalecki.BankApi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private long id;
    @Column(name = "AMOUNT")
    private double price;
    @Column(name = "TRANSACTION_DATE")
    private OffsetDateTime dateTime;
    @Column(name = "FROM_ACCOUNT_ID")
    //@JoinColumn(name = "FROM_ACCOUNT_ID")
    private long fromAccountId;
    @Column(name = "TO_ACCOUNT_ID")
    //@JoinColumn(name = "TO_ACCOUNT_ID")
    private long toAccountId;
}
