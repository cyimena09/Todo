package be.ifosup.todo;

import be.ifosup.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAOImpl implements TodoDAO {
    // ATTRIBUTES
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTOR
    public TodoDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODS
    @Override
    public List<Todo> getTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();

        connection = daoFactory.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(
                "SELECT todos.id, todos.description, categories.name as category " +
                        "FROM todos " +
                        "INNER JOIN categories ON categories.id = todos.FK_category");

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String description = resultSet.getString("description");
            String category = resultSet.getString("category");

            Todo todo = new Todo(id, description, null, category);
            todos.add(todo);
        }
        return todos;
    }

    @Override
    public void createTodo(Todo todo) {
        try {
            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO todos (description, FK_category) VALUES (?, ?)");
            preparedStatement.setString(1, todo.getDescription());
            preparedStatement.setLong(2, todo.getFK_category());

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deleteTodo(Long id) {
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM todos WHERE id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateTodo(Long id) {
        // a faire !
    }

}
