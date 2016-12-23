package com.zxytech.web.servlet.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by ryan on 2016/12/23.
 */
@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC驱动名及数据库URL
    // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Deprecated
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://172.16.4.196:3310/app_djtestframework";

    // 数据库的用户名与密码
    static final String USER = "djtestframework";
    static final String PASSWD = "djtestframework";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement statement = null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet MySQL测试";
        String htmlDocument = "";
        htmlDocument += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n";
        try {
            // 注册JDBC驱动
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASSWD);

            // 执行查询
            System.out.println("实例化Statement对...");
            statement = conn.createStatement();
            String sql;
            sql = "SELECT id, action_time, user_id, content_type_id, object_id, object_repr FROM django_admin_log LIMIT 10;";

            ResultSet resultSet = statement.executeQuery(sql);

            htmlDocument += "<table><tr><th>id</th><th>action_time</th><th>user_id</th><th>content_type</th><th>object_id</th><th>object_repr</th></tr>\n";
            while (resultSet.next()) {
                // 通过字段检索
                int id = resultSet.getInt("id");
                String action_time = resultSet.getString("action_time");
//                Date action_time = resultSet.getDate("action_time");
                int user_id = resultSet.getInt("user_id");
                int content_type_id = resultSet.getInt("content_type_id");
                int object_id = resultSet.getInt("object_id");
                String obejct_repr = resultSet.getString("object_repr");

                // 输出数据
                System.out.println("id: " + id + " action_time: " + action_time + " user_id: " + user_id + " content_type_id:" + content_type_id + " object_id: " + object_id + " object_repr:" + obejct_repr);

//                htmlDocument += "id: " + id + " action_time: " + action_time + " user_id: " + user_id + " content_type_id:" + content_type_id + " object_id: " + object_id + " object_repr:" + obejct_repr + "<br>\n";
                htmlDocument += "<tr><td>" + id + "</td><td>" + action_time + "</td><td>" + user_id + "</td><td>" + content_type_id + "</td><td>" + object_id + "</td><td>" + obejct_repr + "</td></tr>\n";
            }
            htmlDocument += "</table>";
            // 完成后关闭
            resultSet.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.println(htmlDocument);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
