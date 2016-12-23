package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ryan on 2016/12/23.
 */
@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // 设置响应类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "Servlet 异常处理";
        String htmlDocument = "";
        htmlDocument += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n";
        htmlDocument += "<h1>菜鸟教程异常信息实例演示</h1>\n";
        if (throwable == null && statusCode == null) {
            htmlDocument += "<h2>错误信息丢失</h2>\n";
            htmlDocument += "请返回 <a href=\"" +
                    response.encodeURL("http://localhost:8080/") +
                    "\">主页</a>。\n";
        } else if (statusCode != null) {
            htmlDocument += "错误代码 : " + statusCode;
        } else {
            htmlDocument += "<h2>错误信息</h2>\n";
            htmlDocument += "Servlet Name : " + servletName +
                    "</br></br>\n";
            htmlDocument += "异常类型 : " +
                    throwable.getClass().getName() +
                    "</br></br>\n";
            htmlDocument += "请求 URI: " + requestUri +
                    "<br><br>\n";
            htmlDocument += "异常信息: " +
                    throwable.getMessage();
        }
        out.println(htmlDocument);
    }
}
