package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.*;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserRepository userRepository;
    private HashingService hashingService;

    public LoginService (UserRepository userRepository, HashingService hashingService) {

        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    public void login (String username, String password) {

        if (!userRepository.existsUserByUsername(username)) {
            throw UserDoesntExist.from(username);
        }
        User userToLogin = userRepository.findUsersByUsername(username);
        if (!hashingService.comparePassword(password, userToLogin.getPassword())) {
            throw IncorrectPasswordException.from(userToLogin.getUsername());
        }
    }
}
