package be.ifosup.todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDAO {

    List<Todo> getTodos() throws SQLException;

    void createTodo(Todo todo);

    void updateTodo(Long id);

    void deleteTodo(Long id);

}
