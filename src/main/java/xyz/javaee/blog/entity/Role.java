package xyz.javaee.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="xyz-javaee-blog-entity-Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Meow.`role`")
public class Role implements Serializable {
    /**
     * 用户角色
     */
    @TableId(value = "role_id",type = IdType.AUTO)
    @ApiModelProperty(value="用户角色")
    private Integer roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /**
     * 角色名称EN
     */
    @TableField(value = "role_name_en")
    @ApiModelProperty(value="角色名称EN")
    private String roleNameEn;

    private static final long serialVersionUID = 1L;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_ROLE_NAME_EN = "role_name_en";
}
