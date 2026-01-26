package alfred.projects.investor.Service;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Persistance.SessionPersistance;
import alfred.projects.investor.Persistance.UserPersistance;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    private UserPersistance userPersistance;
    private SessionPersistance sessionPersistance;

    public LoginService (UserPersistance userPersistance, SessionPersistance sessionPersistance) {
        this.userPersistance = userPersistance;
        this.sessionPersistance = sessionPersistance;
    }

    public void login (String username, String password, String sessionId) {

        userPersistance.testUserExists(username);
        userPersistance.testCredentials(username, password);
        sessionPersistance.addSession(sessionId, Session.from(username));
    }
}
