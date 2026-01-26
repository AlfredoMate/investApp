package alfred.projects.investor.Persistance;

public class UserAlreadyExists extends RuntimeException {
    private UserAlreadyExists(String message) {
        super(message);
    }

    public static UserAlreadyExists from (String username) {
        String message = username + " username already exists.";
        return new UserAlreadyExists(message);
    }
}
