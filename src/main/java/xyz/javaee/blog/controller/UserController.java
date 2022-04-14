package xyz.javaee.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.blog.constants.SecurityConstants;
import xyz.javaee.blog.constants.UserConstants;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.entity.converter.UserConverter;
import xyz.javaee.blog.entity.vo.UserVO;
import xyz.javaee.blog.service.UserService;
import xyz.javaee.blog.utils.JwtTokenUtils;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;

/**
 * @author loveliness
 */
@RestController
@Api(tags = "用户个人信息")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/getUserInfo")
    @ApiOperation("修改信息时,获取用户信息")
    public Result getUserInfo(@ApiParam("用户的token 用来获取用户id") @RequestHeader(SecurityConstants.TOKEN_HEADER) String token) {
        token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        String userId = JwtTokenUtils.getId(token);
        User user = userService.getById(userId);
        UserVO userVO = userConverter.userToVO(user);
        return Result.ok().data(userVO);
    }

    @PostMapping("/modifyInfMail")
    @ApiOperation("获取邮箱验证码,用于修改信息时")
    public Result modifyInfMail(@RequestHeader(SecurityConstants.TOKEN_HEADER) String token,
                                @ApiParam("随机生成的鉴权码") @RequestParam String captchaVerification,
                                @ApiParam("验证码") @RequestParam String code) {
        if (userService.checkCaptcha(captchaVerification, code)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
            String userId = JwtTokenUtils.getId(token);
            return userService.modifyInfMail(userId);
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/updateUserInfo")
    @ApiOperation("修改用户信息")
    public Result updateUserInfo(@ApiParam("要更改的用户信息") @RequestBody User user,
                                 @RequestHeader(SecurityConstants.TOKEN_HEADER) String token,
                                 @ApiParam("随机生成的鉴权码，邮箱里的") @RequestParam String mailCode) {

        //通过请求头的id获取用户，增加安全性
        token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        String userId = JwtTokenUtils.getId(token);
        User user1 = userService.getById(userId);
        if (userService.checkCaptcha(UserConstants.MODIFY_INFORMATION_MAIL + user1.getEmail(), mailCode)) {
            //防止通过接口修改邮箱
            user.setEmail(null);
            user.setUserId(userId);
            //密码进行加密
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userService.updateById(user)) {
                return Result.ok().message("修改成功");
            } else {
                return Result.error().message("修改失败");
            }
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }
}
