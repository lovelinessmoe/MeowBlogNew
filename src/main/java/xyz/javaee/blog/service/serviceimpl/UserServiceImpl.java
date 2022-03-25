package xyz.javaee.blog.service.serviceimpl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.dao.UserMapper;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.service.UserService;
import xyz.javaee.blog.utils.Result;

import java.util.concurrent.TimeUnit;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StringRedisTemplate stringRedisTemplate;


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

    @Override
    public boolean checkCaptcha(String captchaVerification, String code) {
        //通过随机码获取验证码
        String captchaCode = stringRedisTemplate.opsForValue().get(captchaVerification);
        //Redis中删除对应的验证码
        stringRedisTemplate.delete(captchaVerification);
        return code.equals(captchaCode);
    }

    @Override
    public Result generateCaptcha() {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 30, 5, 5);
        String captchaVerification = "captcha" + IdUtil.fastSimpleUUID();
        String code = captcha.getCode();
        stringRedisTemplate.opsForValue().set(captchaVerification, code);
        stringRedisTemplate.expire(captchaVerification, 60 * 5, TimeUnit.SECONDS);
        return Result.ok().data("img", captcha.getImageBase64Data())
                .data("captchaVerification", captchaVerification);
    }
}
