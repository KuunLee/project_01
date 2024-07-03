package com.kli.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AbcFilter已拦截到请求");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("AbcFilter已放行请求");
    }
}
