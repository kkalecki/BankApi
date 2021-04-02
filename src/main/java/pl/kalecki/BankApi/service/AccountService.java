package pl.kalecki.BankApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.controller.dto.AccountRequest;
import pl.kalecki.BankApi.controller.dto.AccountResponse;
import pl.kalecki.BankApi.repository.AccountRepository;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.service.mapper.AccountMapper;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public List<AccountResponse> findAll() {
        List<Account> all = repository.findAll();
        return mapper.mapAccountsToAccountResponses(all);
    }


    public AccountResponse save(AccountRequest accountRequest) {

        Account account = mapper.mapAccountRequestToAccount(accountRequest);
        Account savedAccount = repository.save(account);

        return mapper.mapAccountToAccountResponse(savedAccount);
    }


    public AccountResponse findById(Long id) {

        Account account = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with this id does not exists"));
        return mapper.mapAccountToAccountResponse(account);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
