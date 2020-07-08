package ru.kuznetsovnn.webapp;
 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logDir = getServletContext().getInitParameter("logdir");
        request.setAttribute("logDir", logDir);
        request.getRequestDispatcher("/views/settings.jsp").forward(request, response);
    }
}