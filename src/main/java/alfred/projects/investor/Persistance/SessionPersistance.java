package alfred.projects.investor.Persistance;

import alfred.projects.investor.Model.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionPersistance {

    Map<String, Session> allSessions = new HashMap<>();


    public void addSession (String sessionId, Session session) {
        allSessions.put(sessionId, session);
    }
    public Session getSession (String sessionId) {
        return allSessions.get(sessionId);
    }
}
