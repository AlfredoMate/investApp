package alfred.projects.investor.Persistance;

import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;

@Repository
public class UserPersistance {
    private Map<String, String> users = new HashMap<>();

    public Boolean addUser (String username, String password) {

        if (userExists(username)) {
            return true;
        }
       users.put(username, password);
        return false;
    }

    public boolean userExists (String username) {
        return users.containsKey(username);
    }

    public boolean testCredentials (String username, String password) {
        if (!userExists(username)) {
            return false;
        }
        return users.get(username).equals(password);
    }
}
