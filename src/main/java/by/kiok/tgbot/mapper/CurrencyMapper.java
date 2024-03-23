package by.kiok.tgbot.mapper;

import by.kiok.tgbot.dto.request.CurrencyRequest;
import by.kiok.tgbot.dto.responce.CurrencyResponse;
import by.kiok.tgbot.model.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {

    Currency currencyRequestToCurrency(CurrencyRequest currencyRequest);

    CurrencyResponse currencyToCurrencyResponse(Currency currency);
    Currency currencyResponseToCurrency(CurrencyResponse currencyResponse);
}
