package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.IncorrectPasswordException;
import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserDoesntExist;
import alfred.projects.investor.Persistance.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private HashingService hashingService;

    @InjectMocks
    private LoginService loginService;

    private final String USERNAME = "Joe";

    private final String PASSWORD = "password";

    @Test
    @DisplayName("Happy flow test")
    public void loginHappyPath () {

        User user = new User(USERNAME, PASSWORD);
        given(userRepository.existsUserByUsername(USERNAME)).willReturn(true);
        given(userRepository.findUsersByUsername(USERNAME)).willReturn(user);
        given(hashingService.comparePassword(eq(PASSWORD), anyString())).willReturn(true);
        loginService.login(USERNAME, PASSWORD);
        verify(userRepository).findUsersByUsername(USERNAME);
        verify(hashingService).comparePassword(eq(PASSWORD), anyString());

    }

    @Test
    @DisplayName("User does not exist flow")
    public void userDoesntExistFlow () {
        given(userRepository.existsUserByUsername(USERNAME)).willReturn(false);

        assertThrows(UserDoesntExist.class,
                () -> loginService.login(USERNAME, PASSWORD)
        );

    }

    @Test
    @DisplayName("Incorrect password flow")
    public void incorrectPasswordFlow (){
        User user = new User(USERNAME, PASSWORD);
        given(userRepository.existsUserByUsername(USERNAME)).willReturn(true);
        given(hashingService.comparePassword(eq(PASSWORD), anyString())).willReturn(false);
        given(userRepository.findUsersByUsername(USERNAME)).willReturn(user);
        assertThrows(IncorrectPasswordException.class,
                () -> loginService.login(USERNAME, PASSWORD));
    }
}
