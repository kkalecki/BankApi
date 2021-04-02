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

    @PostMapping("/account")
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest) {

        return service.save(accountRequest);

    }

    @GetMapping("/accounts")
    public List<AccountResponse> showAccounts() {

        return service.findAll();

    }

    @GetMapping("/account")
    public AccountResponse showAccount(@RequestParam Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/account")
    public void deleteAccount(@RequestParam Long id) {
        service.deleteById(id);
    }
}
