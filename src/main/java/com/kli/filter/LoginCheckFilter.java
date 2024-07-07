package com.kli.filter;

import com.alibaba.fastjson.JSONObject;
import com.kli.pojo.Result;
import com.kli.util.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.强转为http请求
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        StringBuffer requestURL = req.getRequestURL();
        log.info("请求url为：{}", requestURL);

        //2.如果是登录则不拦截
        if (StringUtils.contains(requestURL, "login")) {
                log.info("登录操作");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3.获取token信息

        String token = req.getHeader("token");

        //4.token为空，返回未登录的信息
        if (StringUtils.isBlank(token)) {
            log.info("token为空");
            Result not_login = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(not_login);
            res.getWriter().write(jsonString);
            return;
        }

        //5.解析token，若解析失败，也返回未登录
        try {
            JwtUtils.parseJwt(token);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            log.info("解析令牌失败");
            Result not_login = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(not_login);
            res.getWriter().write(jsonString);
            return;
        }

        //6.解析成功，放行
        log.info("令牌合法，进行放行操作");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}