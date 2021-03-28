package pl.kalecki.BankApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.repository.AccountRepository;
import pl.kalecki.BankApi.repository.entity.Account;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }


    public Account save(Account account) {
        return repository.save(account);
    }


    public Optional<Account> findById(Long aLong) {
        return repository.findById(aLong);
    }


    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
