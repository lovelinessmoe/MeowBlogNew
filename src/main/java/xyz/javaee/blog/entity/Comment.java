package xyz.javaee.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 文章评论
    */
@ApiModel(value="xyz-javaee-blog-entity-Comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Meow.`comment`")
public class Comment implements Serializable {
    @TableId(value = "comment_id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value="")
    private String commentId;

    /**
     * 评论人
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="评论人")
    private Integer userId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="评论内容")
    private String content;

    /**
     * 0h5 1md
     */
    @TableField(value = "type")
    @ApiModelProperty(value="0h5 1md")
    private Boolean type;

    /**
     * 创建时间
     */
    @TableField(value = "creat_time")
    @ApiModelProperty(value="创建时间")
    private Date creatTime;

    /**
     * 父id
     */
    @TableField(value = "p_id")
    @ApiModelProperty(value="父id")
    private Integer pId;

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

    public static final String COL_TYPE = "type";

    public static final String COL_CREAT_TIME = "creat_time";

    public static final String COL_P_ID = "p_id";

    public static final String COL_LEVEL = "level";

    public static final String COL_ARTICLE_ID = "article_id";
}
