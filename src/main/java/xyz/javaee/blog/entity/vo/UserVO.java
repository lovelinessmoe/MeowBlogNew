package xyz.javaee.blog.entity.vo;

import lombok.Data;

/**
 * @author loveliness
 */
@Data
public class UserVO {

    /**
     * 昵称
     */
    private String userName;

    /**
     * 电话号
     */
    private String telephone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatarUrl;

}
