package be.ifosup.dao;

import be.ifosup.category.CategoryDAO;
import be.ifosup.category.CategoryDAOImpl;
import be.ifosup.todo.TodoDAO;
import be.ifosup.todo.TodoDAOImpl;
import be.ifosup.user.UserDAO;
import be.ifosup.user.UserDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    // ATTRIBUTES
    private final String url;
    private final String userName;
    private final String password;

    // CONSTRUCTOR
    public DAOFactory(String url, String userName, String password){
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    // METHODS
    // Load driver
    public static DAOFactory getInstance(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/poo?serverTimezone=CET", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    // Get Dao for sql tables
    public UserDAO getUserDAO(){
        return new UserDAOImpl(this);
    }

    public TodoDAO getTodoDAO(){
        return new TodoDAOImpl(this);
    }

    public CategoryDAO getCategoryDAO() { return new CategoryDAOImpl(this); }
}
