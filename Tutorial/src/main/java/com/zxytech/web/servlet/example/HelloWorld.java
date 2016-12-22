package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ryan on 2016/12/22.
 */

public class HelloWorld extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        // 执行必须的初始化
        message = "Hello World.";
        System.out.println("HelloWorld::init() method.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置相应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
        System.out.println("HelloWorld::doGet() method.");
    }

    public void destroy() {
        // 什么也不做
//        System.out.println("HelloWorld::destory() method.");
    }
}
