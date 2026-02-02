package alfred.projects.investor.Service;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Persistance.*;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private SessionPersistance sessionPersistance;
    private UserRepository userRepository;


    public LoginService (SessionPersistance sessionPersistance,
    UserRepository userRepository) {
        this.sessionPersistance = sessionPersistance;
        this.userRepository = userRepository;
    }

    public void login (String username, String password, String sessionId) {

        if (!userRepository.existsUserByUsername(username)) {
            throw UserDoesntExist.from(username);
        }
        User userToLogin = userRepository.findUsersByUsername(username);
        if (!HashingService.comparePassword(password, userToLogin.getPassword())) {
            throw IncorrectPasswordException.from(userToLogin.getUsername());
        }
        sessionPersistance.addSession(sessionId, Session.from(username));
    }
}
