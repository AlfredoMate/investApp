package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserAlreadyExists;
import alfred.projects.investor.Persistance.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository userRepository;

    public RegisterService (UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    public void registerLogic (String username, String password) {

        String hashedPassword = HashingService.hashPassword(password);

        if (userRepository.existsUserByUsername(username)) {
            throw UserAlreadyExists.from(username);
        }
        User user = new User(username, hashedPassword);
        userRepository.save(user);
    }
}
