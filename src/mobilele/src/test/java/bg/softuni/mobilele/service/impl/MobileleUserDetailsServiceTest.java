package bg.softuni.mobilele.service.impl;


import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileleUserDetailsServiceTest {

    private MobileleUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new MobileleUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pesho@softuni.bg")
        );
    }

    @Test
    void testUserFoundException() {
        //Arrange
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        //Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getEmail());

        //Assert
        assertNotNull(userDetails);

        assertEquals(testUserEntity.getEmail(), userDetails.getUsername(),
                "Username is not mapped to email!");

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "This user is not admin");
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "This user is not user");

    }

    private static UserEntity createTestUser() {
        return new UserEntity()
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("pesho@softuni.bg")
                .setActive(false)
                .setPassword("topsecret")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails.getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

}
