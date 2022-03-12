package xyz.javaee.blog.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.db.nosql.redis.RedisDS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import xyz.javaee.blog.entity.JwtUser;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.service.AuthService;
import xyz.javaee.blog.service.UserService;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;


/**
 * @author lovleiness
 * @description 认证授权
 **/
@RestController
@RequestMapping("/auth")
@Api(tags = "认证")
@AllArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@ApiParam("用户Bean") @RequestBody User loginRequest,
                        @ApiParam("随机生成的鉴权码") @RequestParam String captchaVerification,
                        @ApiParam("验证码") @RequestParam String code) {
        //创建Redis连接
        Jedis jedis = RedisDS.create().getJedis();
        //通过随机码获取验证码
        String captchaCode = jedis.get(captchaVerification);
        //Redis中删除对应的验证码
        jedis.del(captchaVerification);
        if (code.equals(captchaCode)) {
            return authService.createToken(loginRequest);
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public Result logout() {
        return authService.removeToken();
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User userRegister,
                           @ApiParam("随机生成的鉴权码") @RequestParam String captchaVerification,
                           @ApiParam("验证码") @RequestParam String code) {
        //创建Redis连接
        Jedis jedis = RedisDS.create().getJedis();
        //通过随机码获取验证码
        String captchaCode = jedis.get(captchaVerification);
        //Redis中删除对应的验证码
        jedis.del(captchaVerification);
        if (code.equals(captchaCode)) {
            userService.register(userRegister);
            return Result.ok();
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }


    @PostMapping("/captcha")
    @ApiOperation("获取验证码")
    public Result captcha() {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(123, 38, 5, 20);

        String captchaVerification = "captcha" + Math.random();
        String code = captcha.getCode();
        Jedis jedis = RedisDS.create().getJedis();
        jedis.set(captchaVerification, code);

        jedis.expire(captchaVerification, (long) 60 * 5);
        return Result.ok().data("img", captcha.getImageBase64Data())
                .data("captchaVerification", captchaVerification);
    }

}
