package xyz.javaee.blog.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import xyz.javaee.blog.service.UserService;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.service.UserService;

/**
 * @author lovleiness
 * @description 获取当前请求的用户
 */
@Component
@AllArgsConstructor
public class CurrentUserUtils {

    private UserService userService;

    public User getCurrentUser() {
        User user = new User();
        user.setEmail(getCurrentEmail());
        return userService.login(user);
    }

    private String getCurrentEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
