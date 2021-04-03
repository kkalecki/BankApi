package pl.kalecki.BankApi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {

    private  String amount;
    private  String base;
    private  LocalDate date;
    private  Map<String, Double> rates;
}
