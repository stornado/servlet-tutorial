package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by ryan on 2016/12/23.
 */
@WebServlet("/ReadCookies")
public class ReadCookies extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        // 获取与该域相关的Cookie数组
        cookies = request.getCookies();
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = "Servlet 读取Cookie";
        String htmlDocument = "";

        htmlDocument += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n";
        if (cookies != null) {
            htmlDocument += "<h2>Cookie 名称和值</h2>\n";
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                htmlDocument += "名称：" + cookie.getName() + "，";
                htmlDocument += "值：" + URLDecoder.decode(URLEncoder.encode(URLDecoder.decode(cookie.getValue(), "UTF-8"), "ISO8859-1"), "UTF-8") + " <br/>\n";
            }
        } else {
            htmlDocument += "<h2>No Cookie founds</h2>\n";
        }
        htmlDocument += "</body>\n";
        htmlDocument += "</html>\n";
        out.println(htmlDocument);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
