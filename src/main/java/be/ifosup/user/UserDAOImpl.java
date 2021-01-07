package be.ifosup.user;

import be.ifosup.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    // ATTRIBUTES
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultat = null;

    // CONSTRUCTOR
    public UserDAOImpl(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    // METHODS
    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        connection = daoFactory.getConnection();
        statement = connection.createStatement();
        resultat = statement.executeQuery("SELECT * FROM USERS");

        while(resultat.next()) {
            String nom = resultat.getString("name");
            String pseudo = resultat.getString("pseudo");
            String pass = resultat.getString("password");

            User user = new User();
            user.setName(nom);
            user.setPseudo(pseudo);
            user.setPassword(pass);

            users.add(user);
        }
        return users;
    }

    @Override
    public void createUser(User user) throws SQLException {
        connection = daoFactory.getConnection();

        preparedStatement = connection.prepareStatement("INSERT INTO User(name, pseudo, password) VALUES (?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPseudo());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();
    }

    @Override
    public Boolean isValid(String pseudo, String password) throws SQLException {
        connection = daoFactory.getConnection();

        preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE pseudo = ?");
        preparedStatement.setString(1, pseudo);

        resultat = preparedStatement.executeQuery();

        if (resultat.next()) {
            // check password
            if (resultat.getString("password").toUpperCase().equals(password)) {
                System.out.println("Le mot de passe est correct");
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Aucun compte avec ce pseudo");
            return false;
        }
    }

}
