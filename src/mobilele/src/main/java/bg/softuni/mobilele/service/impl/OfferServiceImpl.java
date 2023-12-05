package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.CreateOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.MonitoringService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.service.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final MonitoringService monitoringService;
    private final UserRepository userRepository;


    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelRepository modelRepository,
                            MonitoringService monitoringService, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.monitoringService = monitoringService;
        this.userRepository = userRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails seller) {
        OfferEntity newOffer = map(createOfferDTO);

        UserEntity sellerEntity = userRepository.findByEmail(seller.getUsername())
                .orElseThrow(() -> new ObjectNotFoundException("User with email " + seller.getUsername() + " not found."));

        newOffer.setSeller(sellerEntity);
        offerRepository.save(newOffer);

        return newOffer.getUuid();
    }

    @Override
    public Page<OfferSummaryDTO> getAllOffers(Pageable pageable) {
        monitoringService.logOfferSearch();

        return offerRepository
                .findAll(pageable)
                .map(OfferServiceImpl::mapAsSummary);
    }

    @Override
    public Optional<OfferDetailDTO> getOfferDetail(UUID uuid, UserDetails viewer) {

        return offerRepository
                .findFirstByUuid(uuid)
                .map(o -> this.mapAsDetails(o, viewer));
    }

    @Override
    @Transactional
    public void deleteOffer(UUID uuid) {
        offerRepository.deleteByUuid(uuid);
    }

    @Override
    public boolean isOwner(UUID uuid, String username) {
        OfferEntity offerEntity = offerRepository.findFirstByUuid(uuid)
                .orElse(null);

        return isOwner(offerEntity, username);
    }

    private boolean isOwner(OfferEntity offerEntity, String username) {
        if (offerEntity == null || username == null) {
            return false;
        }

        UserEntity userEntity = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new ObjectNotFoundException("Unknown user ..."));

        if (isAdmin(userEntity)) {
            return true;
        }

        return offerEntity.getSeller().getEmail().equals(username);
    }

    private boolean isAdmin(UserEntity userEntity) {
        return userEntity.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(r -> r.equals(UserRoleEnum.ADMIN));
    }

    private OfferDetailDTO mapAsDetails(OfferEntity offerEntity, UserDetails viewer) {

        return new OfferDetailDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl(),
                offerEntity.getSeller().getFirstName(),
                isOwner(offerEntity, viewer != null ? viewer.getUsername() : null));
    }

    private static OfferSummaryDTO mapAsSummary(OfferEntity offerEntity) {
        return new OfferSummaryDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl());
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
