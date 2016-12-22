package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ryan on 2016/12/22.
 */
@WebServlet("/CheckBox")
public class CheckBox extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "读取复选框数据";
        String htmlDocument = "";
        try {
            htmlDocument += "<!DOCTYPE html> \n" +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<ul>\n" +
                    "  <li><b>菜鸟按教程标识：</b>: "
                    + request.getParameter("runoob") + "\n" +
                    "  <li><b>Google 标识：</b>: "
                    + request.getParameter("google") + "\n" +
                    "  <li><b>Netflix：</b>: "
                    + request.getParameter("netflix") + "\n" +
                    "</ul>\n" +
                    "</body></html>";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        out.println(htmlDocument);
    }

    // 处理post方法请求的数据
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
