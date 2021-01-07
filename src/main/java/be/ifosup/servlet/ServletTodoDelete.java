package be.ifosup.servlet;

import be.ifosup.dao.DAOFactory;
import be.ifosup.todo.TodoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletTodoDelete", urlPatterns = "/delete-todo")
public class ServletTodoDelete extends HttpServlet {
    // ATTRIBUTES
    private TodoDAO todoDAO;

    // METHODS
    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.todoDAO = daoFactory.getTodoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get id
        String id = request.getParameter("id");
        System.out.println(id);
        // call delete method
        todoDAO.deleteTodo(Long.parseLong(id));
        try{
            request.setAttribute("todos", todoDAO.getTodos());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/todos.jsp").forward(request, response);
    }

}
