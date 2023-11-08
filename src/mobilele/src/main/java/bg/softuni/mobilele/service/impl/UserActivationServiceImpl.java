package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entity.UserActivationCodeEntity;
import bg.softuni.mobilele.model.events.UserRegisteredEvent;
import bg.softuni.mobilele.repository.UserActivationCodeRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.EmailService;
import bg.softuni.mobilele.service.UserActivationService;
import bg.softuni.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationCodeRepository userActivationCodeRepository;

    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent event) {
        String activationCode = createActivationCode(event.getUserEmail());
        emailService.sendRegistrationEmail(
                event.getUserEmail(),
                event.getUserNames(),
                activationCode);
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {
        //TODO: Implement
//        System.out.println("NOT YET");
    }

    @Override
    public String createActivationCode(String userEmail) {
        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));

        userActivationCodeRepository.save(userActivationCodeEntity);

        return activationCode;
    }

    private static String generateActivationCode() {
        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randomInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randomInx));
        }

        return activationCode.toString();
    }


}
