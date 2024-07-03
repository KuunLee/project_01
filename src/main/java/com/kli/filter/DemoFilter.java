package com.kli.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    /**
     * 初始化方法
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化方法执行了");
    }

    /**
     * 兰姐到请求之后调用
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DemoFilter已拦截到请求");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("DemoFilter已放行请求");
    }

    /**
     * 销毁的方法
     */
    @Override
    public void destroy() {
        System.out.println("销毁方法执行了");
    }
}
