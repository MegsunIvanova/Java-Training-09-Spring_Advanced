package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.CreateOfferDTO;

import java.util.UUID;

public interface OfferService {
    UUID createOffer(CreateOfferDTO createOfferDTO);
}
