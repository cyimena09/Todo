package be.ifosup.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(urlPatterns = "*")
public class ServletFilters implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getSession().getAttribute("pseudo") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("login").forward(servletRequest, servletResponse);
            //System.out.println("Accès interdit");
        }
    }

    @Override
    public void destroy() {

    }
}
