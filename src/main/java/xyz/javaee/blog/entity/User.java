package xyz.javaee.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 * @author loveliness
 */
@ApiModel(value = "xyz-javaee-blog-entity-User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Meow.`user`")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id", condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    /**
     * 昵称
     */
    @TableField(value = "user_name", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "昵称")
    private String userName;

    /**
     * 电话号
     */
    @TableField(value = "telephone", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "电话号")
    private String telephone;

    /**
     * 年龄
     */
    @TableField(value = "age", condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 登录密码，加密后保存
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "登录密码，加密为md5格式后保存")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 头像
     */
    @TableField(value = "avatar_url")
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_TELEPHONE = "telephone";

    public static final String COL_AGE = "age";

    public static final String COL_PASSWORD = "password";

    public static final String COL_EMAIL = "email";

    public static final String COL_AVATAR_URL = "avatar_url";
}
