package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.controller.dto.TransactionRequest;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.repository.entity.Transaction;
import pl.kalecki.BankApi.repository.entity.User;
import pl.kalecki.BankApi.service.TransactionService;
import pl.kalecki.BankApi.service.mapper.TransactionMapper;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
@RequestMapping("/api/v1")
public class TransactionController {
    private final TransactionService service;
    private final TransactionMapper mapper;

    @PostMapping("/transaction")
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = mapper.mapTransactionRequestToTransaction(transactionRequest);

        Transaction saved_transaction = service.save(transaction);
        TransactionResponse transactionResponse = mapper.mapTransactionToTransactionResponse(saved_transaction);

        return transactionResponse;

    }

    @GetMapping("/transactions")
    public List<TransactionResponse> showTransactions() {
        List<Transaction> all = service.findAll();

        List<TransactionResponse> transactionResponses = mapper.mapTransactionsToTransactionResponses(all);

        return transactionResponses;

    }

    @GetMapping("/transaction")
    public TransactionResponse showTransaction(@RequestParam Long id) {

        Transaction transaction = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with this id does not exists"));

        TransactionResponse transactionResponse = mapper.mapTransactionToTransactionResponse(transaction);
        return transactionResponse;
    }

    @DeleteMapping("/transaction")
    public void deleteTransaction(@RequestParam Long id) {
        service.deleteById(id);
    }


}
