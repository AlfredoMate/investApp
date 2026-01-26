package alfred.projects.investor.Service;

import alfred.projects.investor.Persistance.UserPersistance;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserPersistance userPersistance;

    public RegisterService (UserPersistance userPersistance) {
        this.userPersistance = userPersistance;
    }

    public void registerLogic (String username, String password) {

        String hashedPassword = HashingService.hashPassword(password);
        userPersistance.addUser(username, hashedPassword);

    }
}
