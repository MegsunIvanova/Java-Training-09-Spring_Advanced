package bg.softuni.mobilele.testutils;

import bg.softuni.mobilele.model.entity.ExchangeRateEntity;
import bg.softuni.mobilele.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestData {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(new ExchangeRateEntity()
                .setCurrency(currency)
                .setRate(rate)
        );
    }

    public void cleanAllTestDate() {
        exchangeRateRepository.deleteAll();
    }
}
