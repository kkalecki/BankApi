package pl.kalecki.BankApi.service.mapper;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kalecki.BankApi.service.dto.CurrencyResponse;

import java.util.HashMap;
import java.util.Map;
@Component
public class CurrencyMapper {

    public ResponseEntity<CurrencyResponse> mapJSONtoCurrencyResponse(String currency)
    {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity("https://api.frankfurter.app/latest?base={currency}", CurrencyResponse.class,currency);
    }
}
