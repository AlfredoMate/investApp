package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserAlreadyExists;
import alfred.projects.investor.Persistance.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private HashingService hashingService;

    public RegisterService (UserRepository userRepository, HashingService hashingService) {

        this.userRepository = userRepository;
        this.hashingService = hashingService;

    }

    public void registerLogic (String username, String password) {

        String hashedPassword = hashingService.hashPassword(password);

        if (userRepository.existsUserByUsername(username)) {
            throw UserAlreadyExists.from(username);
        }
        User user = new User(username, hashedPassword);
        userRepository.save(user);
    }
}
