package bg.softuni.mobilele.testutils;

import bg.softuni.mobilele.model.entity.*;
import bg.softuni.mobilele.model.enums.CategoryEnum;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class TestDataUtil {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private BrandRepository brandRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(new ExchangeRateEntity()
                .setCurrency(currency)
                .setRate(rate));
    }

    public OfferEntity createTestOffer(UserEntity owner) {

        BrandEntity brandEntity = brandRepository.save(new BrandEntity()
                .setName("Test brand")
                .setModels(List.of(
                        new ModelEntity().setName("Test Model").setCategory(CategoryEnum.CAR),
                        new ModelEntity().setName("Test Model1").setCategory(CategoryEnum.MOTORCYCLE)
                )));

        OfferEntity offerEntity = new OfferEntity()
                .setModel(brandEntity.getModels().get(0))
                .setImageUrl("https://www.google.com")
                .setPrice(BigDecimal.valueOf(1000))
                .setYear(2020)
                .setUuid(UUID.randomUUID())
                .setDescription("Test Description")
                .setEngine(EngineEnum.GASOLINE)
                .setMileage(10000)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setSeller(owner);

        return offerRepository.save(offerEntity);
    }

    public void cleanUp() {
        exchangeRateRepository.deleteAll();
        offerRepository.deleteAll();
        brandRepository.deleteAll();
    }


}
