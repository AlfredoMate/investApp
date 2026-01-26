package alfred.projects.investor.Persistance;

public class IncorrectPasswordException extends RuntimeException {
    private IncorrectPasswordException(String message) {
        super(message);
    }

    public static IncorrectPasswordException from (String username) {
        String message = "Incorrect password for user " + username;
        return new IncorrectPasswordException(message);
    }
}
