package alfred.projects.investor.Service;

import org.mindrot.jbcrypt.BCrypt;

public class HashingService {

    private static final int LOG_ROUNDS = 12;

    public static String hashPassword (String password) {

        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return  BCrypt.hashpw(password, salt);
    }

    public static boolean comparePassword (String password, String hashedStoredPassword) {
        return BCrypt.checkpw(password, hashedStoredPassword);
    }
}
