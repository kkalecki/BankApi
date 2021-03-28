package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.service.AccountService;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService service;
    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account)
    {
        return service.save(account);

    }
    @GetMapping("/accounts")
    public List<Account> showAccounts()
    {
        return service.findAll();

    }
    @GetMapping("/account")
    public Account showAccount(@RequestParam Long id)
    {
        return service.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with this id does not exists"));
    }
    @DeleteMapping("/account")
    public void deleteAccount(@RequestParam Long id)
    {
        service.deleteById(id);
    }
}
