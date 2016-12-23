package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by ryan on 2016/12/22.
 */
@WebServlet("/HelloForm")
public class HelloForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HelloForm() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 为名字创建Cookie
        Cookie cookieName = new Cookie("name", URLEncoder.encode(request.getParameter("name"), "UTF-8"));//中文转码
        Cookie url = new Cookie("url", request.getParameter("url"));

        // 为两个Cookie设置过期时间秒
        cookieName.setMaxAge(60);
        url.setMaxAge(60 * 2);

        // 在响应头中添加两个Cookie
        response.addCookie(cookieName);
        response.addCookie(url);

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "使用POST方法读取表单数据";

        try {
            // 处理中文
            String name = new String(request.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
            String htmlDocument = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<ul>\n" +
                    "  <li><b>站点名</b>：" + name + "</li>\n" +
                    "  <li><b>网址</b>：" + request.getParameter("url") + "</li>\n" +
                    "</ul>\n" +
                    "</body></html>";
            out.println(htmlDocument);
        } catch (NullPointerException e) {
            out.println();
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
