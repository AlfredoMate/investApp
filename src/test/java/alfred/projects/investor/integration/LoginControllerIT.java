package alfred.projects.investor.integration;

import alfred.projects.investor.Controllers.LoginController;
import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserRepository;
import alfred.projects.investor.Service.HashingService;
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
public class LoginControllerIT {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LoginController loginController;
    @Autowired
    private HashingService hashingService;

    @Autowired
    private UserRepository userRepository;

    private final String EXISTING_USERNAME = "Joe";
    private final String UNEXISTING_USERNAME = "John";
    private final String PASSWORD = "password";
    private final String INCORRECT_PASSWORD = "incorrect_password";

    @BeforeEach
    public void setUp () {
        userRepository.deleteAll();
        String password_hashed = hashingService.hashPassword(PASSWORD);
        User user = new User(EXISTING_USERNAME, password_hashed);
        userRepository.save(user);
    }

    @DisplayName("Happy path integration test for validateLogin method")
    @Test
    public void testValidateLoginHappyPath () throws Exception {
        mockMvc.perform(post("/login")
                .header("Username", EXISTING_USERNAME)
                .header("Password", PASSWORD))
                .andExpect(status().isOk());
    }

    @DisplayName("Integration test for ValidateLogin mehtod when user doesn't exist.")
    @Test
    public void testValidateLoginUserDoesntExist () throws Exception {
        mockMvc.perform(post("/login")
                .header("Username", UNEXISTING_USERNAME)
                .header("Password", PASSWORD))
                .andExpect(status().is(404));
    }

    @DisplayName("Integration test for ValidateLogin mehtod when password is incorrect.")
    @Test
    public void testValidateLoginUserIncorrectPssword () throws Exception {
        mockMvc.perform(post("/login")
                .header("Username", EXISTING_USERNAME)
                .header("Password", INCORRECT_PASSWORD))
                .andExpect(status().is(401));
    }
}
