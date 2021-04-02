package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.controller.dto.AccountRequest;
import pl.kalecki.BankApi.controller.dto.AccountResponse;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.service.AccountService;
import pl.kalecki.BankApi.service.mapper.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService service;
    private final AccountMapper mapper;

    @PostMapping("/account")
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest)
    {
        Account account = mapper.mapAccountRequestToAccount(accountRequest);

        Account savedAccount = service.save(account);

        AccountResponse accountResponse = mapper.mapAccountToAccountResponse(savedAccount);
        return accountResponse;

    }
    @GetMapping("/accounts")
    public List<AccountResponse> showAccounts()
    {
        List<Account> all = service.findAll();

        List<AccountResponse> accountResponses = mapper.mapAccountsToAccountResponses(all);

        return accountResponses;

    }
    @GetMapping("/account")
    public AccountResponse showAccount(@RequestParam Long id)
    {
        Account account = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with this id does not exists"));

        AccountResponse accountResponse = mapper.mapAccountToAccountResponse(account);
        return accountResponse;
    }

    @DeleteMapping("/account")
    public void deleteAccount(@RequestParam Long id)
    {
        service.deleteById(id);
    }
}
