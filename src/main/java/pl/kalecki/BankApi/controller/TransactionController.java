package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.controller.dto.TransactionRequest;
import pl.kalecki.BankApi.controller.dto.TransactionResponse;
import pl.kalecki.BankApi.service.TransactionService;
import pl.kalecki.BankApi.service.mapper.TransactionMapper;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1")
public class TransactionController {
    private final TransactionService service;
    private final TransactionMapper mapper;

    @PostMapping("/transaction")
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {


        return service.save(transactionRequest);

    }

    @GetMapping("/transactions")
    public List<TransactionResponse> showTransactions() {


        return service.findAll();

    }

    @GetMapping("/transaction")
    public TransactionResponse showTransaction(@RequestParam Long id) {


        return service.findById(id);
    }

    @DeleteMapping("/transaction")
    public void deleteTransaction(@RequestParam Long id) {
        service.deleteById(id);
    }


}
