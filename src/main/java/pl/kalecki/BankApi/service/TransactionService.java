package pl.kalecki.BankApi.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.repository.AccountRepository;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.repository.entity.Transaction;
import pl.kalecki.BankApi.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public List<Transaction> findAll() {
        return transactionRepository.findAll();

    }


    public Transaction save(Transaction transaction) {

        Account fromAcc = accountRepository.findById(transaction.getFromAccountId()).orElseThrow(() -> new IllegalArgumentException("Account with this Id doesnt exist"));
        Account toAcc = accountRepository.findById(transaction.getToAccountId()).orElseThrow(() -> new IllegalArgumentException("Account with this Id doesnt exist"));

        if (fromAcc.getBalance() < transaction.getPrice()) {
            throw new IllegalArgumentException("You do not have enough money");
        } else {
            fromAcc.setBalance(fromAcc.getBalance() - transaction.getPrice());
            toAcc.setBalance(toAcc.getBalance() + transaction.getPrice());
        }


        return transactionRepository.save(transaction);

    }


    public Optional<Transaction> findById(Long aLong) {
        return transactionRepository.findById(aLong);

    }


    public void deleteById(Long aLong) {
        transactionRepository.deleteById(aLong);

    }
}
