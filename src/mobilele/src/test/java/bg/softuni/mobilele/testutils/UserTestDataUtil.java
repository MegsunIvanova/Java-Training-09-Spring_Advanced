package bg.softuni.mobilele.testutils;

import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserEntity createTestUser() {
        return  createUser(List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin() {
       return createUser(List.of(UserRoleEnum.ADMIN));
    }

    public UserEntity createUser(List<UserRoleEnum> roles) {
        List<UserRoleEntity> rolesEntities = userRoleRepository.findAllByRoleIn(roles);

        UserEntity newUser = new UserEntity()
                .setActive(true)
                .setEmail("test@example.com")
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(rolesEntities);

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }


}
