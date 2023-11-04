package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.CreateOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    UUID createOffer(CreateOfferDTO createOfferDTO);
    Page<OfferSummaryDTO> getAllOffers(Pageable pageable);
    Optional<OfferDetailDTO> getOfferDetail(UUID uuid);
    void deleteOffer(UUID uuid);
}
