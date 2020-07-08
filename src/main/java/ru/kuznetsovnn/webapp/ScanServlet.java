package ru.kuznetsovnn.webapp;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
 
public class ScanServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Start scanning...");
        request.getRequestDispatcher("/views/scan.jsp").forward(request, response);
    }
}