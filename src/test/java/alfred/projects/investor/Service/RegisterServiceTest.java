package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserAlreadyExists;
import alfred.projects.investor.Persistance.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private HashingService hashingService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterService registerService;

    private final String USERNAME = "Joe";
    private final String PASSWORD = "password";

    @DisplayName("Happy flow")
    @Test
    public void registerLogicTestHappyPath () {

        registerService.registerLogic(USERNAME, PASSWORD);
        verify(hashingService).hashPassword(PASSWORD);
        verify(userRepository).existsUserByUsername(USERNAME);
        verify(userRepository).save(any(User.class));

    }

    @Test
    @DisplayName("Test to return UserAlreadyExists exception")
    public void registerLogicTestUserAlreadyExistsFlow() {

        given(userRepository.existsUserByUsername(USERNAME)).willReturn(true);
        assertThrows(
                UserAlreadyExists.class,
                () -> registerService.registerLogic(USERNAME, PASSWORD)
        );

    }
}
