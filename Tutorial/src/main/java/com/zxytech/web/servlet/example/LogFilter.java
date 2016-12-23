package com.zxytech.web.servlet.example;

import javax.servlet.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryan on 2016/12/23.
 */
public class LogFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数
        String site = filterConfig.getInitParameter("Site");

        // 输出初始化参数 输出一次
        System.out.println("网站名称：" + site);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 请求一次输出一次
        Calendar calendar = Calendar.getInstance();
        Date nowTime = calendar.getTime();
        System.out.println("LogFilter: " + nowTime);

        // 把请求传回过滤链
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        // 在Filter实例被Web容器从服务器移除之前调用
    }
}
