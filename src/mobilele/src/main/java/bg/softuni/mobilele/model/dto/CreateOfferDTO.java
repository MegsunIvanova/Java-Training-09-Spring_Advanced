package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.model.validation.YearNotInTheFuture;
import jakarta.validation.constraints.*;

import java.util.Objects;

public record CreateOfferDTO(
        @NotEmpty @Size(min = 5, max = 512) String description,
        @Positive @NotNull Long modelId,
        @NotNull EngineEnum engine,
        @NotNull TransmissionEnum transmission,
        @NotEmpty String imageUrl,
        @Positive @NotNull Integer mileage,
        @Positive @NotNull Integer price,
        @YearNotInTheFuture
        @NotNull (message = "Year must be provided.")
        @Min(value = 1930, message = "Year must be greater than or equal to 1930.")
        Integer year
        ) {
    public static CreateOfferDTO empty() {
        return new CreateOfferDTO(null, null, null, null, null, null, null, null);
    }
}


