package alfred.projects.investor.Persistance;

public class UserDoesntExist extends RuntimeException {
    private UserDoesntExist(String message) {
        super(message);
    }

    public static UserDoesntExist from (String username) {
        String message = "User " + username + " does not exist";
        return new UserDoesntExist(message);
    }
}
