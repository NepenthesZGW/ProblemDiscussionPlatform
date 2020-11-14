package com.zgw.registryone.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 忘忧症
 * @Distribute
 */

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("===初始化监听器=====");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (((HttpServletRequest)request).getRequestURI().startsWith("/eureka")){
            System.out.println(((HttpServletRequest)request).getRequestURI());
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
