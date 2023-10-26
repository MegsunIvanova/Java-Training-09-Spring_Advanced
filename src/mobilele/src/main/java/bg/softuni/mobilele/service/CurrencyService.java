package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ExchangeRatesDTO;

public interface CurrencyService {
    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);
}
