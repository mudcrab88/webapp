package ru.kuznetsovnn.webapp;

import java.io.File;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
 
public class ScanServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Start scanning...");
        String logPath = "C:\\Projects\\nodejs\\logreader\\data";
        File logDir = new File(logPath);
        File[] arrLogDirs = logDir.listFiles();
        for( int i = 0; i < arrLogDirs.length; i++ ){
            if (arrLogDirs[i].isDirectory()) {
                File[] arrLogFiles = arrLogDirs[i].listFiles();
                for( int j = 0; j < arrLogFiles.length; j++ ) {
                    System.out.println(arrLogFiles[j].getAbsolutePath());
                }
            }
        }
        request.getRequestDispatcher("/views/scan.jsp").forward(request, response);
    }
}