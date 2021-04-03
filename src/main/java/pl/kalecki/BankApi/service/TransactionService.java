package pl.kalecki.BankApi.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.controller.dto.TransactionRequest;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.repository.AccountRepository;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.repository.entity.Transaction;
import pl.kalecki.BankApi.repository.TransactionRepository;
import pl.kalecki.BankApi.service.dto.CurrencyResponse;
import pl.kalecki.BankApi.service.mapper.CurrencyMapper;
import pl.kalecki.BankApi.service.mapper.TransactionMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper mapper;
    private final CurrencyMapper currencyService;


    public List<TransactionResponse> findAll() {
        List<Transaction> all = transactionRepository.findAll();
        List<TransactionResponse> transactionResponses = mapper.mapTransactionsToTransactionResponses(all);
        return transactionResponses;

    }


    public TransactionResponse save(TransactionRequest transactionRequest) {

        Transaction transaction = mapper.mapTransactionRequestToTransaction(transactionRequest);

        Account fromAcc = accountRepository.findById(transaction.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account with this Id does not exist"));
        Account toAcc = accountRepository.findById(transaction.getToAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account with this Id does not exist"));

        String fromAccCurrency = fromAcc.getCurrency();
        System.out.println(fromAccCurrency);
        String toAccCurrency = toAcc.getCurrency();
        System.out.println(toAccCurrency);
        ResponseEntity<CurrencyResponse> currencyResponse = currencyService.mapJSONtoCurrencyResponse(fromAccCurrency);

        double currencyRate = currencyResponse.getBody().getRates().get(toAccCurrency);
        System.out.println(currencyRate);

        if (fromAcc.getBalance() < transaction.getPrice()) {
            throw new IllegalArgumentException("You do not have enough money");
        } else {
            fromAcc.setBalance(fromAcc.getBalance() - transaction.getPrice());
            System.out.println(transaction.getPrice()*currencyRate);
            toAcc.setBalance(toAcc.getBalance() + (transaction.getPrice()*currencyRate));

            Transaction savedTransaction = transactionRepository.save(transaction);

            return mapper.mapTransactionToTransactionResponse(savedTransaction);
        }


    }


    public TransactionResponse findById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction with this id does not exists"));

        TransactionResponse transactionResponse = mapper.mapTransactionToTransactionResponse(transaction);


        return transactionResponse;

    }


    public void deleteById(Long aLong) {
        transactionRepository.deleteById(aLong);

    }
}
