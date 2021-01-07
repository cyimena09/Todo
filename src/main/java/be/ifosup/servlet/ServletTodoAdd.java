package be.ifosup.servlet;

import be.ifosup.category.CategoryDAO;
import be.ifosup.dao.DAOFactory;
import be.ifosup.todo.Todo;
import be.ifosup.todo.TodoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletTodoAdd", urlPatterns = "/add-todo")
public class ServletTodoAdd extends HttpServlet {
    private TodoDAO todoDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.todoDAO = daoFactory.getTodoDAO();
        this.categoryDAO = daoFactory.getCategoryDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // force UTF-8
        request.setCharacterEncoding("UTF-8");

        // get form values
        String description = request.getParameter("description");
        String strFK_category = request.getParameter("categories");

        // convert string FK_category to long
        Long FK_category = Long.parseLong(strFK_category);

        // add in db
        todoDAO.createTodo(new Todo(null, description, FK_category, null));

        // redirection
        try {
            request.setAttribute("todos", todoDAO.getTodos());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/todos.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("categories", categoryDAO.getCategories());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/todoAdd.jsp").forward(request, response);
    }
}
