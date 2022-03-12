package xyz.javaee.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.blog.entity.User;

/**
 * @Description:
 * @Author loveliness
 * @Date 2021/10/5 4:01 下午
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    /**
     * 登陆接口
     *
     * @param loginUser 存在用户名和密码的用户实体
     * @return 根据实体条件查出的用户
     */
    User login(User loginUser);

    /**
     * 比对数据库的密码和传进来的密码加密后是否相同
     *
     * @param currentPassword 原来的密码
     * @param password        加密后的密码
     * @return 相同t 不相同f
     */
    boolean check(String currentPassword, String password);

    /**
     * 用户注册
     *
     * @param userRegister 注册的用户实体
     * @return 失败-1 成功影响的条数1
     */
    int register(User userRegister);
}


