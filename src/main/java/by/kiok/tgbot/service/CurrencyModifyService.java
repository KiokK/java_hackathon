package by.kiok.tgbot.service;

import by.kiok.tgbot.dto.request.CurrencyRequest;
import by.kiok.tgbot.dto.responce.CurrencyResponse;
import by.kiok.tgbot.mapper.CurrencyMapper;
import by.kiok.tgbot.model.Currency;
import by.kiok.tgbot.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyModifyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper = Mappers.getMapper(CurrencyMapper.class);
    private final BigDecimal VAL_50 = new BigDecimal(50);

    public int update(CurrencyRequest currencyRequest) throws EntityNotFoundException {
        Currency currency = currencyMapper.currencyRequestToCurrency(currencyRequest);
        Currency findCurrency = currencyRepository.updateAndGetOld(currency)
                .orElseThrow();

        int modificationPercentage = currency.getPrice().subtract(findCurrency.getPrice())
                .divide(currency.getPrice().add(findCurrency.getPrice()))
                .multiply(VAL_50).intValueExact();

        return Math.abs(modificationPercentage);
    }

    public void create(List<CurrencyResponse> currencyList) throws EntityNotFoundException {
        List<Currency> currencyResponseList = currencyList.stream()
                .map(currencyMapper::currencyResponseToCurrency)
                .toList();
        currencyRepository.create(currencyResponseList);
    }
}
