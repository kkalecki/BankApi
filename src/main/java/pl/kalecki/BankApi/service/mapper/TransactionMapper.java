package pl.kalecki.BankApi.service.mapper;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.kalecki.BankApi.controller.dto.TransactionRequest;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.controller.dto.UserRequest;
import pl.kalecki.BankApi.controller.dto.UserResponse;
import pl.kalecki.BankApi.repository.entity.Transaction;
import pl.kalecki.BankApi.repository.entity.User;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    public TransactionResponse mapTransactionToTransactionResponse(Transaction transaction)
    {
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .id(transaction.getId())
                .price(transaction.getPrice())
                .fromAccountId(transaction.getFromAccountId())
                .toAccountId(transaction.getToAccountId())
                .build();

        return transactionResponse;
    }

    public Transaction mapTransactionRequestToTransaction(TransactionRequest transactionRequest)
    {
        Transaction transaction = Transaction.builder()
                .price(transactionRequest.getPrice())
                .dateTime(OffsetDateTime.now())
                .fromAccountId(transactionRequest.getFromAccountId())
                .toAccountId(transactionRequest.getToAccountId())
                .build();
        return transaction;
    }

    public List<TransactionResponse> mapTransactionsToTransactionResponses(List<Transaction> all)
    {
        List<TransactionResponse> transactionResponses = all.stream().map(transaction -> TransactionResponse.builder()
                .id(transaction.getId())
                .price(transaction.getPrice())
                .fromAccountId(transaction.getFromAccountId())
                .toAccountId(transaction.getToAccountId())
                .build())
                .collect(Collectors.toList());
        return transactionResponses;
    }
}
