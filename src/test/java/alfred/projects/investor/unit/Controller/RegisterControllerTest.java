package alfred.projects.investor.unit.Controller;

import alfred.projects.investor.Controllers.RegisterController;
import alfred.projects.investor.Service.RegisterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private RegisterController registerController;

    private final String USERNAME = "Joe";
    private final String PASSWORD = "password";

    @Test
    @DisplayName("Happy path flow unit test of register method")
    public void registerHappyPathFlow() {
        registerController.register(USERNAME, PASSWORD);
        verify(registerService).registerLogic(USERNAME, PASSWORD);
    }
}
