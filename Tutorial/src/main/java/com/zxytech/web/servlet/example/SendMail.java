package com.zxytech.web.servlet.example;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by ryan on 2016/12/24.
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 收件人的点赞邮件ID
        String to = "servlet-tutorial@zxytech.com";
        // 发件人的邮件地址
        String from = "noreply@zxytech.com";
        // 假设你是从本地主机发送邮件
        String host = "localhost";

        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        // 获取默认的Session对象
        Session session = Session.getDefaultInstance(properties);

        // 设置响应类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 创建一个默认的MimeMessage对象
            MimeMessage message = new MimeMessage(session);
            // 设置From：header field of the header
            message.setFrom(new InternetAddress(from));
            // 设置To：header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 设置Subject：header field
            message.setSubject("这是邮件主题");
            // 设置实际消息
            // message.setText("这是邮件内容");
            // 发送HTML消息，内容大小不限
            String content = "<h1>邮件正文</h1><br>\n<p>这是一封HTML邮件</p>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送消息
            Transport.send(message);

            String title = "发送电子邮件";
            String res = "成功发送消息...";
            String htmlDocument = "";
            htmlDocument += "<!DOCTYPE html> \n" +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<p align=\"center\">" + res + "</p>\n" +
                    "</body></html>";
            out.println(htmlDocument);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
