package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by ryan on 2016/12/23.
 */
@WebServlet("/DisplayHeader")
public class DisplayHeader extends HttpServlet {
    // 处理GET请求的方法
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应头
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet HTTP Header请求示例";
        String htmlDocument = "";
        try {
            htmlDocument += "<!DOCTYPE html> \n" +
                    "<html>\n" +
                    "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                    "<tr bgcolor=\"#949494\">\n" +
                    "<th>Header 名称</th><th>Header 值</th>\n" +
                    "</tr>\n";

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String paramName = (String) headerNames.nextElement();
                htmlDocument += "<tr><td>" + paramName + "</td>\n";
                String paramValue = (String) request.getHeader(paramName);
                htmlDocument += "<td> " + paramValue + "</td></tr>\n";
            }
            htmlDocument += "</table>\n</body></html>";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        out.println(htmlDocument);
    }

    // 处理POST请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
