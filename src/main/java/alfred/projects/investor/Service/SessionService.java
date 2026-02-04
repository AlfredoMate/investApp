package alfred.projects.investor.Service;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Persistance.SessionPersistance;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {

    private SessionPersistance sessionPersistance;

    public SessionService (SessionPersistance sessionPersistance) {
        this.sessionPersistance = sessionPersistance;
    }

     public String createSession (String username) {

        String sessionId = UUID.randomUUID().toString();
        sessionPersistance.addSession(sessionId, Session.from(username));
        return sessionId;

    }

    public ResponseCookie createCookie(String sessionId) {
        return ResponseCookie.from("SESSIONID", sessionId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60*60)
                .sameSite("None")
                .build();
    }

    public Session getSession(String sessionId) {
        return sessionPersistance.getSession(sessionId);
    }
}
