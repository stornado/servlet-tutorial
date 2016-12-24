package com.zxytech.web.servlet.example;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ryan on 2016/12/24.
 */
public class SiteHitCounter implements Filter {
    private int hitCount;

    public void init(FilterConfig filterConfig) throws ServletException {
        // 重置点击计数器
        hitCount = 0;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 把计数器的值增加1
        ++hitCount;

        // 输出计数器
        System.out.println("网站访问统计： " + hitCount);

        // 把请求传回到过滤器链
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        // 这一步是可选的，但是如果需要，您可以把 hitCount 的值写入到数据库
    }
}
