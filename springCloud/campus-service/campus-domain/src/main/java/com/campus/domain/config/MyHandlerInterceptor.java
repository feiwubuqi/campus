package com.campus.domain.config;

import com.auth0.jwt.JWT;
import com.campus.domain.TokenUtils;
import com.campus.domain.model.AccountInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("开始拦截.........");

        boolean flag=false;
//        Enumeration er=request.getHeaderNames();
//        while(er.hasMoreElements()) {
//            String name = (String) er.nextElement();
//            String value = request.getHeader(name);
//            System.out.println(name + "=" + value);
//        }
        String token=request.getHeader("authentication-token");
//        System.out.println("token:"+token);
        if (StringUtils.isBlank(token)){
            response.setStatus(401);
            return false;}
        boolean status= TokenUtils.verity(token);
        if (!status){
            response.setStatus(401);
            response.addHeader("REDIRECT", "REDIRECT");
            response.addHeader("CONTEXTPATH", "/login");
        }else{
            flag=true;
        }
        //业务代码
        return flag;
    }

    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }



}
