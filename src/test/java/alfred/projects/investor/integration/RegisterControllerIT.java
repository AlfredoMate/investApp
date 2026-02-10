package alfred.projects.investor.integration;

import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final String USERNAME = "Joe";
    private final String ALREADY_EXISTING_USERNAME = "John";
    private final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        User alreadyExistingUser = new User(ALREADY_EXISTING_USERNAME, PASSWORD);
        userRepository.save(alreadyExistingUser);
    }

    @DisplayName("Happy path integration test for register method")
    @Test
    void testRegister() throws Exception {
        mockMvc.perform(post("/register")
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                ).andExpect(status().isOk());
    }

    @DisplayName("Integration test for register method when user already exists.")
    @Test
    void testRegisterUserAlreadyExists() throws Exception {
        mockMvc.perform(post("/register")
                .header("username", ALREADY_EXISTING_USERNAME)
                .header("password", PASSWORD))
                .andExpect(status().is(409));
    }
}
