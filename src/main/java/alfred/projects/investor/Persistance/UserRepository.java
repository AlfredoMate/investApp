package alfred.projects.investor.Persistance;

import alfred.projects.investor.Service.HashingService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbc;

    public UserRepository (JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void storeUser (User user) {

        checkUserExists(user);
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbc.update(sql, user.getUsername(), user.getPassword());
    }

    public void authenticate(User user) {
        checkUserDoesntExist(user);
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
          User userFound = new User();
          userFound.setId(r.getInt("id"));
          userFound.setUsername(r.getString("username"));
          userFound.setPassword(r.getString("password"));
          return userFound;
        };
        List<User> users = jdbc.query(sql, userRowMapper, user.getUsername());
        if (!HashingService.comparePassword(user.getPassword(), users.get(0).getPassword())) {
            throw IncorrectPasswordException.from(user.getUsername());
        }
    }

    public void checkUserDoesntExist(User user) {
        String sql = "SELECT EXISTS (SELECT 1 FROM users WHERE username = ? )";
        Boolean exists = jdbc.queryForObject(sql, Boolean.class, user.getUsername());
        if (Boolean.FALSE.equals(exists)) {
            throw UserDoesntExist.from(user.getUsername());
        }
    }
    public void checkUserExists(User user) {
        String sql = "SELECT EXISTS (SELECT 1 FROM users WHERE username = ?)";
        Boolean exists = jdbc.queryForObject(sql, Boolean.class, user.getUsername());
        if (Boolean.TRUE.equals(exists)) {
            throw UserAlreadyExists.from(user.getUsername());
        }
    }
}
