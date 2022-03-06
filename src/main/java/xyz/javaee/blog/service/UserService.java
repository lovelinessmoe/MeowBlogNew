package xyz.javaee.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.blog.entity.User;

/**
 * @Description:
 * @Author loveliness
 * @Date 2021/10/5 4:01 下午
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    User login(User loginUser);

    boolean check(String currentPassword, String password);

    int register(User userRegister);
}


