package alfred.projects.investor.integration;

import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserRepository;
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
public class RegisterControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final String USERNAME = "Joe";
    private final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }
    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(post("/register")
                        .header("username", USERNAME)
                        .header("password", PASSWORD)
                ).andExpect(status().isOk());
    }
}
