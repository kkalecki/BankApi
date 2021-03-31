package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.controller.dto.AccountRequest;
import pl.kalecki.BankApi.controller.dto.AccountResponse;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService service;

    @PostMapping("/account")
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest)
    {
        Account account = Account.builder()
                .balance(accountRequest.getBalance())
                .currency(accountRequest.getCurrency())
                .userId(accountRequest.getUserId())
                .build();

        Account savedAccount = service.save(account);

        AccountResponse accountResponse = AccountResponse.builder()
                .id(savedAccount.getId())
                .balance(savedAccount.getBalance())
                .currency(savedAccount.getCurrency())
                .userId(savedAccount.getUserId())
                .transactionsFrom(account.getTransactions_from())
                .transactionsTo(account.getTransactions_to())
                .build();
        return accountResponse;

    }
    @GetMapping("/accounts")
    public List<AccountResponse> showAccounts()
    {
        List<Account> all = service.findAll();

        List<AccountResponse> accountResponses = all.stream().map(account -> AccountResponse.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .userId(account.getUserId())
                .transactionsFrom(account.getTransactions_from())
                .transactionsTo(account.getTransactions_to())
                .build())
                .collect(Collectors.toList());

        return accountResponses;

    }
    @GetMapping("/account")
    public AccountResponse showAccount(@RequestParam Long id)
    {
        Account account = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with this id does not exists"));

        AccountResponse accountResponse = AccountResponse.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .transactionsFrom(account.getTransactions_from())
                .transactionsTo(account.getTransactions_to())
                .build();
        return accountResponse;
    }

    @DeleteMapping("/account")
    public void deleteAccount(@RequestParam Long id)
    {
        service.deleteById(id);
    }
}
