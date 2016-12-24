package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ryan on 2016/12/24.
 */
@WebServlet("/PageRedirect")
public class PageRedirect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应类型
        response.setContentType("text/html;charset=UTF-8");

        // 要重定向的新位置
        String site = "http://www.runoob.com";

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);

//        response.sendRedirect(site);
    }
}
