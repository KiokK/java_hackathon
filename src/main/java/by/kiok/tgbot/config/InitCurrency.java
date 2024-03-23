package by.kiok.tgbot.config;

import by.kiok.tgbot.dto.responce.CurrencyResponse;
import by.kiok.tgbot.exception.FailInitException;
import by.kiok.tgbot.mapper.CurrencyMapper;
import by.kiok.tgbot.model.Currency;
import by.kiok.tgbot.service.CurrencyModifyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "init-currency", havingValue = "true")
public class InitCurrency {

    private final CurrencyModifyService currencyModifyService;
    private final CurrencyMapper currencyMapper = Mappers.getMapper(CurrencyMapper.class);

    @Order
    @PostConstruct
    public void init() {
        List<CurrencyResponse> currencyResponseList = getCourse();
        currencyModifyService.create(currencyResponseList);
    }

    private List<CurrencyResponse> getCourse() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate
                .getForEntity("https://api.mexc.com/api/v3/ticker/price", List.class);

        if (response.getStatusCode().is2xxSuccessful()) {

            return response.getBody();
        }

        throw new FailInitException();
    }
}
