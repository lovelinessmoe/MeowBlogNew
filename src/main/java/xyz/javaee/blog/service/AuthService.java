package xyz.javaee.blog.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import xyz.javaee.blog.entity.JwtUser;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.utils.CurrentUserUtils;
import xyz.javaee.blog.utils.JwtTokenUtils;
import xyz.javaee.blog.utils.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lovleiness
 **/
@Service
@AllArgsConstructor
public class AuthService {
    private final xyz.javaee.blog.service.UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CurrentUserUtils currentUserUtils;


    public JwtUser createToken(User loginRequest) {
        User user = userService.login(loginRequest);

        if (user == null) {
            throw new BadCredentialsException("用户名或密码不正确");
        }

        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("用户名或密码不正确");
        }
        JwtUser jwtUser = new JwtUser(user);

        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("用户被禁止登陆");
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getUserId(), authorities, true);
        stringRedisTemplate.opsForValue().set(user.getUserId(), token);
        jwtUser.setToken(token);

        return jwtUser;
    }

    public Result removeToken() {
        try {
            String userId = currentUserUtils.getCurrentUser().getUserId();
            stringRedisTemplate.delete(userId);
        } catch (Exception e) {
           return Result.error().message("用户不存在");
        }
        return Result.ok();
    }
}
