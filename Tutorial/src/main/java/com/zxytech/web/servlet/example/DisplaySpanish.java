package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ryan on 2016/12/26.
 */
@WebServlet("/DisplaySpanish")
public class DisplaySpanish extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 设置西班牙语言代码
        response.setHeader("Content-Language", "es");
        String title = "En Espa&ntilde;ol";
        String htmlDocument = "";
        htmlDocument += "<!DOCTYPE html> \n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1>" + "En Espa&ntilde;ol:" + "</h1>\n" +
                "<h1>" + "&iexcl;Hola Mundo!" + "</h1>\n" +
                "</body></html>";
        out.println(htmlDocument);
    }
}
