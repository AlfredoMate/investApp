package alfred.projects.investor.Persistance;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT * FROM user WHERE username = :username")
    User findUsersByUsername (String username);

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE username = :username )")
    boolean existsUserByUsername(String username);

}
