package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RegisterServiceTest {


    @Test
    public void registerLogicTestHappyPath () {

        String username = "Joe";
        String password = "password";
        HashingService hashingService = mock(HashingService.class);
        UserRepository userRepository = mock(UserRepository.class);

        RegisterService registerService = new RegisterService(userRepository, hashingService);
        registerService.registerLogic(username, password);
        verify(hashingService).hashPassword(password);
        verify(userRepository).existsUserByUsername(username);
        verify(userRepository).save(any(User.class));

    }
}
