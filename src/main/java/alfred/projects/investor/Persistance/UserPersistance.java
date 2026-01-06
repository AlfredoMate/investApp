package alfred.projects.investor.Persistance;

import alfred.projects.investor.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Repository
public class UserPersistance {
    private ArrayList<User> users = new ArrayList<>();

    public void addUser (User user) {
        users.add(user);
    }

    public boolean userExists (User user) {
        return users.stream().
                anyMatch(u -> u.getUsername().equals(user.getUsername()));
    }
}
