package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.controller.dto.TransactionRequest;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.repository.entity.Transaction;
import pl.kalecki.BankApi.repository.entity.User;
import pl.kalecki.BankApi.service.TransactionService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
@RequestMapping("/api/v1")
public class TransactionController {
    private final TransactionService service;

    @PostMapping("/transaction")
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder()
                .price(transactionRequest.getPrice())
                .dateTime(OffsetDateTime.now())
                .fromAccountId(transactionRequest.getFromAccountId())
                .toAccountId(transactionRequest.getToAccountId())
                .build();

        Transaction saved_transaction = service.save(transaction);
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .id(saved_transaction.getId())
                .price(saved_transaction.getPrice())
                .fromAccountId(saved_transaction.getFromAccountId())
                .toAccountId(saved_transaction.getToAccountId())
                .build();

        return transactionResponse;

    }

    @GetMapping("/transactions")
    public List<TransactionResponse> showTransactions() {
        List<Transaction> all = service.findAll();

        List<TransactionResponse> transactionResponses = all.stream().map(transaction -> TransactionResponse.builder()
                .id(transaction.getId())
                .price(transaction.getPrice())
                .fromAccountId(transaction.getFromAccountId())
                .toAccountId(transaction.getToAccountId())
                .build())
                .collect(Collectors.toList());

        return transactionResponses;

    }

    @GetMapping("/transaction")
    public TransactionResponse showTransaction(@RequestParam Long id) {

        Transaction transaction = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with this id does not exists"));

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .id(transaction.getId())
                .price(transaction.getPrice())
                .fromAccountId(transaction.getFromAccountId())
                .toAccountId(transaction.getToAccountId())
                .build();
        return transactionResponse;
    }

    @DeleteMapping("/transaction")
    public void deleteTransaction(@RequestParam Long id) {
        service.deleteById(id);
    }


}
