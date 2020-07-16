package ru.kuznetsovnn.webapp;

import java.io.File;
import java.util.stream.Collectors;
import java.util.Date;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.google.gson.Gson;
 
public class ScanServlet extends HttpServlet {
 
    private boolean isScanning;
    private String scanResult;
    private Date lastScanDate;
    private Gson gson;
    private String logPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        isScanning = false;
        gson = new Gson();
        logPath = "\\\\SRV-TOMCAT\\upload_doc";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastScanDateMsg = (lastScanDate == null) ? "не определена" : lastScanDate.toString();
        scanResult = isScanning ? scanResult : messageEncode("В данный момент сканирование не производится. Последняя дата сканирования:" + lastScanDateMsg);
        request.setAttribute("scanResult", scanResult);
        request.getRequestDispatcher("/views/scan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        if (isScanning == false) {
            scanLogDir();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(scanResult));
        response.getWriter().flush();
        response.getWriter().close();
        System.out.println(body);
    }

    public String messageEncode(String message) throws UnsupportedEncodingException {
        return new String(message.getBytes(), "UTF-8");
    }

    protected void scanLogDir() throws UnsupportedEncodingException{
        System.out.println("Start scanning...");
        File logDir = new File(logPath);
        File[] arrLogDirs = logDir.listFiles();
        isScanning = true;
        int i = 0;
        for( i = 0; i < arrLogDirs.length; i++ ){
            if (arrLogDirs[i].isDirectory()) {
                File[] arrLogFiles = arrLogDirs[i].listFiles();
                for( int j = 0; j < arrLogFiles.length; j++ ) {
                    System.out.println(arrLogFiles[j].getAbsolutePath());
                }
                scanResult = messageEncode("В данный момент производится сканирование. Обработано папок: "+(i + 1));
            }
        }
        scanResult = messageEncode("Сканирование завершено. Обработано папок: "+ i);
        lastScanDate = new Date();
        isScanning = false;
    }
}