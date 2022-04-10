package xyz.javaee.blog.controller;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

        if (userService.checkCaptcha(captchaVerification, code)) {
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
                           @ApiParam("验证码") @RequestParam String code) {

        if (userService.checkCaptcha(userRegister.getEmail(), code)) {
            userService.register(userRegister);
            return Result.ok();
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/captcha")
    @ApiOperation("获取验证码")
    public Result captcha() {
        return userService.generateCaptcha();
    }

    @PostMapping("/mail")
    @ApiOperation("获取邮箱验证码,用于注册时")
    public Result mail(@RequestBody User user,
                       @ApiParam("随机生成的鉴权码") @RequestParam String captchaVerification,
                       @ApiParam("验证码") @RequestParam String code) {
        if (userService.checkCaptcha(captchaVerification, code)) {
            return userService.mail(user);
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }
}
