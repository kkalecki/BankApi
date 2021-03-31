package pl.kalecki.BankApi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private long id;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "BALANCE")
    private double balance;
    @Column(name = "USER_ID")
    private long userId;
    @OneToMany(mappedBy = "fromAccountId")
    List<Transaction> transactions_from;
    @OneToMany(mappedBy = "toAccountId")
    List<Transaction> transactions_to;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;

}
