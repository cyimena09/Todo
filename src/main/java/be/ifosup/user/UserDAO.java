package be.ifosup.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getUsers() throws SQLException;

    void createUser(User user) throws SQLException;

    Boolean isValid(String pseudo, String password) throws SQLException;
}
