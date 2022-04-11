package xyz.javaee.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章评论
 *
 * @author loveliness
 */
@ApiModel(value = "xyz-javaee-blog-entity-Comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Meow.`comment`")
public class Comment implements Serializable {

    /**
     * 评论id自动生成
     */
    @TableId(value = "comment_id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "评论id自动生成")
    private String commentId;

    /**
     * 评论人
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "评论人")
    private String userId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="评论内容")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 父id
     */
    @TableField(value = "p_id")
    @ApiModelProperty(value = "父id")
    private String pId;

    /**
     * 层级
     */
    @TableField(value = "level")
    @ApiModelProperty(value="层级")
    private String level;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    @ApiModelProperty(value="文章id")
    private String articleId;

    private static final long serialVersionUID = 1L;

    public static final String COL_COMMENT_ID = "comment_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_CREAT_TIME = "creat_time";

    public static final String COL_P_ID = "p_id";

    public static final String COL_LEVEL = "level";

    public static final String COL_ARTICLE_ID = "article_id";
}
