package alfred.projects.investor.Persistance;

import alfred.projects.investor.Service.HashingService;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;

@Repository
public class UserPersistance {
    private Map<String, String> users = new HashMap<>();

    public void addUser (String username, String password) {

        if (chechUserExists(username)) {
            throw UserAlreadyExists.from(username);
        }
       users.put(username, password);
    }

    public boolean chechUserExists(String username) {
        return users.containsKey(username);
    }

    public void testUserExists (String username) {
        if (!chechUserExists(username)) {
            throw UserDoesntExist.from(username);
        }
    }

    public void testCredentials (String username, String password) {
        chechUserExists(username);

        String hashedPasswordStored = users.get(username);
        boolean correctPassword = HashingService.comparePassword(password, hashedPasswordStored);
        if (!correctPassword) {
            throw IncorrectPasswordException.from(username);
        }
    }
}
