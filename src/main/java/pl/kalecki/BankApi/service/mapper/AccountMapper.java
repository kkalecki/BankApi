package pl.kalecki.BankApi.service.mapper;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.kalecki.BankApi.controller.dto.AccountRequest;
import pl.kalecki.BankApi.controller.dto.AccountResponse;
import pl.kalecki.BankApi.controller.dto.UserRequest;
import pl.kalecki.BankApi.controller.dto.UserResponse;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.repository.entity.User;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AccountMapper {

    public AccountResponse mapAccountToAccountResponse(Account account)
    {
        AccountResponse accountResponse = AccountResponse.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .userId(account.getUserId())
                .transactionsFrom(account.getTransactions_from())
                .transactionsTo(account.getTransactions_to())
                .build();

        return accountResponse;
    }

    public Account mapAccountRequestToAccount(AccountRequest accountRequest)
    {
        Account account = Account.builder()
                .balance(accountRequest.getBalance())
                .currency(accountRequest.getCurrency())
                .userId(accountRequest.getUserId())
                .build();
        return account;
    }

    public List<AccountResponse> mapAccountsToAccountResponses(List<Account> all)
    {
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
}
