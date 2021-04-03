package pl.kalecki.BankApi.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.service.dto.CurrencyResponse;
import pl.kalecki.BankApi.service.mapper.CurrencyMapper;

@Service
@Data
public class CurrencyService {
    private final CurrencyMapper mapper;

    public ResponseEntity<CurrencyResponse> createCurrencyResponse(String currency)
    {

        return mapper.mapJSONtoCurrencyResponse(currency);

    }
}
