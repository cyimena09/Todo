package be.ifosup.category;

import be.ifosup.dao.DAOFactory;
import be.ifosup.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    // ATTRIBUTES
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultat = null;

    // CONSTRUCTOR
    public CategoryDAOImpl(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    // METHODS
    @Override
    public List<Category> getCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();

        connection = daoFactory.getConnection();
        statement = connection.createStatement();
        resultat = statement.executeQuery("SELECT * FROM Categories");

        while(resultat.next()) {
            Long id = resultat.getLong("id");
            String nom = resultat.getString("name");

            Category category = new Category();
            category.setId(id);
            category.setName(nom);

            categories.add(category);
        }
        return categories;
    }


}
