package be.ifosup.servlet;

import be.ifosup.dao.DAOFactory;
import be.ifosup.todo.TodoDAO;
import be.ifosup.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletLogin", urlPatterns = "/login")
public class ServletLogin extends HttpServlet {
    private UserDAO userDAO;
    private TodoDAO todoDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userDAO = daoFactory.getUserDAO();
        this.todoDAO = daoFactory.getTodoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get form fields
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");

        try {
            System.out.println("Tentative de connexion");
            if (userDAO.isValid(pseudo, password)) {
                // creation of a session
                request.getSession().setAttribute("pseudo", pseudo);
                // get todos
                request.setAttribute("todos", todoDAO.getTodos());
                request.getRequestDispatcher("views/todos.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Un problème de validité !");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            }
        } catch (SQLException throwable) {
            System.out.println("Problème de connexion");
            throwable.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/login.jsp").forward(request, response);
    }

//    /**
//     * Hashing with SHA1
//     * @param input String to hash
//     * @return String hashed
//     */
//    public String sha1(String input) {
//        String sha1 = null;
//
//        try {
//            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
//            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
//            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
//
//        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
//            System.out.println(e.getMessage());;
//        }
//        return sha1;
//    }

}
