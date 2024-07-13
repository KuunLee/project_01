package com.kli.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.kli.dbo.Result;
import com.kli.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //在目标资源运行前运行，false代表拦截，true代表放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取请求的url
//        StringBuffer requestURL = request.getRequestURL();
//        log.info("请求url为：{}", requestURL);
//
//        //2.如果是登录则不拦截
////        if (StringUtils.contains(requestURL, "login")) {
////            log.info("登录操作");
////            return true;
////        }
//        //3.获取token信息
//
//        String token = request.getHeader("token");
//
//        //4.token为空，返回未登录的信息
//        if (StringUtils.isBlank(token)) {
//            log.info("token为空");
//            Result not_login = Result.error("NOT_LOGIN");
//            String jsonString = JSONObject.toJSONString(not_login);
//            response.getWriter().write(jsonString);
//            return false;
//        }
//
//        //5.解析token，若解析失败，也返回未登录
//        try {
//            JwtUtils.parseJwt(token);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            log.info("解析令牌失败");
//            Result not_login = Result.error("NOT_LOGIN");
//            String jsonString = JSONObject.toJSONString(not_login);
//            response.getWriter().write(jsonString);
//            return false;
//        }
//
//        //6.解析成功，放行
//        log.info("令牌合法，进行放行操作");
//        return true;

        //1.获取token
        String token = request.getHeader("token");

        //2.解析，失败则进行拦截
        try{
            JwtUtils.parseJwt(token);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            Result not_login = Result.error("NOT_LOGIN");
            String res = JSONObject.toJSONString(not_login);
            response.getWriter().write(res);
            return false;
        }

        //3.放行
        return true;
    }

    //目标方法运行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    //试图渲染完成后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
