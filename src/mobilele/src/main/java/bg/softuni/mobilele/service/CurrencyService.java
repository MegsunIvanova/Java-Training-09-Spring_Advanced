package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ConvertRequestDTO;
import bg.softuni.mobilele.model.dto.ExchangeRatesDTO;
import bg.softuni.mobilele.model.dto.MoneyDTO;

public interface CurrencyService {
    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);

    MoneyDTO convert(ConvertRequestDTO convertRequestDTO);
}
