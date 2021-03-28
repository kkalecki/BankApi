package pl.kalecki.BankApi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;

}
