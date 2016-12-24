package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryan on 2016/12/23.
 */
@WebServlet("/Refresh")
public class Refresh extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置刷新加载时间为5秒
        response.setIntHeader("Refresh", 5);
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        // 使用默认时区和语言环境获得一个日历
        Calendar calendar = Calendar.getInstance();
        // 将Calendar类型转换成Date类型
        Date taskTime = calendar.getTime();
        // 设置日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式输出
        String nowTime = dateFormat.format(taskTime);

        String amOrPm;
        if (calendar.get(Calendar.AM_PM) == 0) {
            amOrPm = "AM";
        } else {
            amOrPm = "PM";
        }
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String currentTime = hour + ":" + minute + ":" + second + " " + amOrPm;

        PrintWriter out = response.getWriter();
        String title = "Servlet 自动刷新Header设置";
        String htmlDocument = "";
        htmlDocument += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<p>当前时间是：" + nowTime + "</p>\n" +
                "<p>当前时间是：" + currentTime + "</p>\n";

        out.println(htmlDocument);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
