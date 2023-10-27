package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.CreateOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {
        OfferEntity newOffer = map(createOfferDTO);
        offerRepository.save(newOffer);

        return newOffer.getUuid();
    }

    private OfferEntity map(CreateOfferDTO createOfferDTO) {
        ModelEntity modelEntity = modelRepository
                .findById(createOfferDTO.modelId())
                .orElseThrow(() -> new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!"));

        return new OfferEntity()
                .setUuid(UUID.randomUUID())
                .setDescription(createOfferDTO.description())
                .setModel(modelEntity)
                .setEngine(createOfferDTO.engine())
                .setTransmission(createOfferDTO.transmission())
                .setImageUrl(createOfferDTO.imageUrl())
                .setMileage(createOfferDTO.mileage())
                .setPrice(BigDecimal.valueOf(createOfferDTO.price()))
                .setYear(createOfferDTO.year());
    }

}
