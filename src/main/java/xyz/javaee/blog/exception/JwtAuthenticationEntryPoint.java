package xyz.javaee.blog.exception;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lovleiness
 * @description AuthenticationEntryPoint 用来解决匿名用户访问需要权限才能访问的资源时的异常
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String s = JSONUtil.toJsonStr(Result.RCode(false,ResultCode.USER_ACCOUNT_EXPIRED));
        response.getWriter().println(s);
    }
}
