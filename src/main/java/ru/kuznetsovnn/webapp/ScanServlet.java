package ru.kuznetsovnn.webapp;

import java.io.File;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.google.gson.Gson;
 
public class ScanServlet extends HttpServlet {
 
    public boolean isScanning;
    private String scanResult;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        isScanning = false;
        scanResult = "Сканирование не производилось";
        gson = new Gson();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        scanResult = messageEncode(scanResult);
        request.setAttribute("scanResult", scanResult);
        request.getRequestDispatcher("/views/scan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        if ((isScanning == false) && (body.equals("scan"))) {
            //throw new ServletException(messageEncode("В данный момент сканирование уже выполняется"));
            System.out.println("Start scanning...");
            String logPath = "\\\\SRV-TOMCAT\\upload_doc";
            File logDir = new File(logPath);
            File[] arrLogDirs = logDir.listFiles();
            isScanning = true;
            for( int i = 0; i < arrLogDirs.length; i++ ){
                if (arrLogDirs[i].isDirectory()) {
                    File[] arrLogFiles = arrLogDirs[i].listFiles();
                    for( int j = 0; j < arrLogFiles.length; j++ ) {
                        System.out.println(arrLogFiles[j].getAbsolutePath());
                    }
                    scanResult = messageEncode("Обработано папок: "+i);
                    request.setAttribute("scanResult", scanResult);
                }
            }
            isScanning = false;
        } else {
            if (body.equals("scan")) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(gson.toJson(scanResult));
                response.getWriter().flush();
                response.getWriter().close();
            }
        }
        System.out.println(body);
    }

    public String messageEncode(String message) throws UnsupportedEncodingException {
        return new String(message.getBytes(), "UTF-8");
    }
}