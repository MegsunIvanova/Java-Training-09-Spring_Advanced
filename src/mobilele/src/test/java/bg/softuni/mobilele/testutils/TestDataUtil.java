package bg.softuni.mobilele.testutils;

import bg.softuni.mobilele.model.entity.ExchangeRateEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.ExchangeRateRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TestDataUtil {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private OfferRepository offerRepository;


    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(new ExchangeRateEntity()
                .setCurrency(currency)
                .setRate(rate)
        );
    }

    public OfferEntity createTestOffer(UserEntity owner) {
        //TODO
        return null;
    }

    public void cleanAllTestDate() {
        exchangeRateRepository.deleteAll();
    }


}
