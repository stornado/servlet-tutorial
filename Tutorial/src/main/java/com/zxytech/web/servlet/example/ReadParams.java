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
 * Created by ryan on 2016/12/22.
 */
@WebServlet("/ReadParams")
public class ReadParams extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "读取所有的表单数据";
        String htmlDocument = "";


        try {
            htmlDocument += "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                    "<tr bgcolor=\"#949494\">\n" +
                    "<th>参数名称</th><th>参数值</th>\n" +
                    "</tr>\n";
            Enumeration paramNames = request.getParameterNames();

            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                htmlDocument += "<tr><td>" + paramName + "</td>\n";
                String[] paramValues =
                        request.getParameterValues(paramName);
                // 读取单个值的数据
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() == 0)
                        htmlDocument += "<td><i>没有值</i></td>\n";
                    else
                        htmlDocument += "<td>" + new String(paramValue.getBytes("ISO8859-1"), "UTF-8") + "</td>\n";
                } else {
                    // 读取多个值的数据
                    htmlDocument += "<td><ul>\n";
                    for (int i = 0; i < paramValues.length; i++) {
                        htmlDocument += "<li>" + paramValues[i];
                    }
                    htmlDocument += "</ul></td>\n";
                }
                htmlDocument += "</tr>";
            }
            htmlDocument += "\n</table>\n</body></html>";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        out.println(htmlDocument);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
