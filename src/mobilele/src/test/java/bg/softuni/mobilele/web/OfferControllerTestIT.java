package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.testutils.TestDataUtil;
import bg.softuni.mobilele.testutils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTestIT {
    private static final String TEST_USER_EMAIL = "user@example.com";
    private static final String TEST_USER_EMAIL2 = "user2@example.com";
    private static final String TEST_ADMIN_EMAIL = "admin@example.com";

    @Autowired
    private TestDataUtil testDataUtil;
    @Autowired
    private UserTestDataUtil userTestDataUtil;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testDataUtil.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @AfterEach
    void tearDown() {
        testDataUtil.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @Test
    void testAnonymousDeletionFails() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser("test@example.com");
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(delete("/offer/{uuid}", offerEntity.getUuid()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));
    }

    @Test
    @WithMockUser(username = TEST_USER_EMAIL, roles = {"USER"})
        //for inject Principal in Security Context
    void testNonAdminUserOwnOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/offers/all"));
    }

    @Test
    @WithMockUser(username = TEST_USER_EMAIL2, roles = {"USER"})
    void testNonAdminUserNotOwnOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER_EMAIL);
        UserEntity viewer = userTestDataUtil.createTestUser(TEST_USER_EMAIL2);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = TEST_ADMIN_EMAIL, roles = {"ADMIN"})
    void testAdminUserOwnOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestAdmin(TEST_ADMIN_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/offers/all"));
    }

    @Test
    @WithMockUser(username = TEST_ADMIN_EMAIL, roles = {"USER", "ADMIN"})
    void testAdminUserNotOwnOffer() throws Exception {
        UserEntity admin = userTestDataUtil.createTestAdmin(TEST_ADMIN_EMAIL);
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/offers/all"));
    }


}
