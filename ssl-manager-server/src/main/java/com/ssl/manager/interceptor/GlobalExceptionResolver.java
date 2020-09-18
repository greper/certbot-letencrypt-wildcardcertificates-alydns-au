package com.ssl.manager.interceptor;

import cn.hutool.json.JSONUtil;
import com.ssl.manager.exceptions.ClientException;
import com.ssl.manager.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
* @Description:    统一异常处理
* @Author:         xiaojunnuo
* @CreateDate:     2018/6/26  12:02
*/
@Component
@Order(-1)
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {
        Result<?> result = null;
        ModelAndView mv = new ModelAndView();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Class<?> targetClass = AopUtils.getTargetClass(handlerMethod.getBean());
            if (null != targetClass.getAnnotation(RestController.class)
                    || null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {
                if(ServletRequestBindingException.class.isAssignableFrom(e.getClass()) ){
                    //请求参数错误
                    result = Result.error(1003,e.getMessage(),null);
                    log.error("参数错误：{}->{}",e.getMessage(),request.getRequestURL(),e);
                }else if (e instanceof ClientException) {
                    //业务失败的异常，如“账号或密码错误”
                    ClientException be = (ClientException) e;
                    result = Result.error(be.getCode(),e.getMessage(),be.getData());
                    response.setStatus(200);
                    log.error("操作失败：{}->{}", e.getMessage(), request.getRequestURL(),e);
                } else if (e instanceof AccessDeniedException) {
                    result = Result.error(403,"对不起，您的权限不足",null);
                    response.setStatus(200);
                    log.error("操作失败：{}->{}", e.getMessage(), request.getRequestURL(),e);
                } else if( e.getMessage() == null ){
                    //空指针异常
                    result = Result.error(1001,"服务器内部错误：空指针异常",null);
                    log.error("空指针异常：{}->{}",e.getMessage(),request.getRequestURL(),e);
                }else if(e.getMessage()!=null && e.getMessage().contains("Read timed out") ){
                    //请求超时
                    int code = 1002;
                    result = Result.error(code,e.getMessage(),null);
                    log.error("请求超时：{}->{}",e.getMessage(),request.getRequestURL(),e);
                } else {
                    //String msg =  "服务器内部错误";
                    result = Result.error(1,"服务器内部错误",null);
                    String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                    log.error("系统异常：{}->{}",message,request.getRequestURL(), e);
                }
                responseResult(response, result);
                return mv;
            }
        }
        //如果返回null，则交给后续的Exceptionresolver处理
        return mv;
    }

    private void responseResult(HttpServletResponse response, Result<?> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSONUtil.toJsonStr(result));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

}
