package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record OfferSummaryDTO(
        String id,
        String brand,
        String model,
        int year,
        int mileage,
        BigDecimal price,
        EngineEnum engine,
        TransmissionEnum transmission,
        String imageUrl
) {
   public String summary() {
        return String.format("%s %s, %d", brand, model, year);
    }

}
