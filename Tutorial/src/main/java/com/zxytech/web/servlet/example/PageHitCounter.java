package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ryan on 2016/12/24.
 */
@WebServlet("/PageHitCounter")
public class PageHitCounter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int hitCount;

    public void init() {
        // 重置点击计数器
        hitCount = 0;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //增加hitCount
        ++hitCount;
        PrintWriter out = response.getWriter();
        String title = "总点击量";
        String htmlDocument = "";
        htmlDocument += "<!DOCTYPE html> \n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">" + hitCount + "</h2>\n" +
                "</body></html>";
        out.println(htmlDocument);
    }

    public void destroy() {
        // 这一步是可选的，但是如果需要，可以把hitCount的值写入到数据库
    }
}
