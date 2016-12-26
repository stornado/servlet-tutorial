package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by ryan on 2016/12/26.
 */
@WebServlet("/PercentageLocale")
public class PercentageLocale extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获取客户端的区域设置
        Locale locale = request.getLocale();
        NumberFormat numberFormat = NumberFormat.getPercentInstance(locale);
        String formattedPercent = numberFormat.format(0.51);

        String title = "特定区域设置的百分比";
        String htmlDocument = "";
        htmlDocument += "<!DOCTYPE html> \n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + formattedPercent + "</h1>\n" +
                "</body></html>";
        out.println(htmlDocument);
    }
}
