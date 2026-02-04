package alfred.projects.investor.integration;

import alfred.projects.investor.Controllers.LoginController;
import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserRepository;
import alfred.projects.investor.Service.HashingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoginControllerIT {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LoginController loginController;
    @Autowired
    private HashingService hashingService;

    @Autowired
    private UserRepository userRepository;

    private final String USERNAME = "Joe";
    private final String PASSWORD = "password";

    @BeforeEach
    public void setUp () {
        userRepository.deleteAll();
        String password_hashed = hashingService.hashPassword(PASSWORD);
        User user = new User(USERNAME, password_hashed);
        userRepository.save(user);
    }

    @Test
    public void testValidateLogin () throws Exception {
        mockMvc.perform(post("/login")
                .header("Username", USERNAME)
                .header("Password", PASSWORD))
                .andExpect(status().isOk());
    }

}
