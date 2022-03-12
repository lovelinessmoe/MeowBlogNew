package xyz.javaee.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.dao.UserMapper;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.service.UserService;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User login(User loginUser) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectOne(userQueryWrapper.eq(User.COL_USER_NAME, loginUser.getUserName()));
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    @Override
    public int register(User userRegister) {
        boolean b = userNameIfExist(userRegister.getUserName());
        if (b) {
            return -1;
        } else {
            userRegister.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
            return userMapper.register(userRegister);
        }
    }

    private boolean userNameIfExist(String username) {
        User user1 = new User();
        user1.setTelephone(username);
        User user = login(user1);
        return user != null;
    }

}
