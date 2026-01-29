package alfred.projects.investor.Service;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Persistance.SessionPersistance;
import alfred.projects.investor.Persistance.User;
import alfred.projects.investor.Persistance.UserPersistance;
import alfred.projects.investor.Persistance.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private SessionPersistance sessionPersistance;
    private UserRepository userRepository;

    public LoginService (SessionPersistance sessionPersistance, UserRepository userRepository) {
        this.sessionPersistance = sessionPersistance;
        this.userRepository = userRepository;
    }

    public void login (String username, String password, String sessionId) {

        User userLogin = new User(username, password);
        userRepository.checkUserDoesntExist(userLogin);
        userRepository.authenticate(userLogin);
        sessionPersistance.addSession(sessionId, Session.from(username));
    }
}
