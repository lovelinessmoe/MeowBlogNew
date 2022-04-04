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
 * @author loveliness
 */
@ApiModel(value = "xyz-javaee-blog-entity-Article")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Meow.article")
public class Article implements Serializable {
    /**
     * 日志自增Id
     */
    @TableId(value = "article_id", type = IdType.INPUT)
    @ApiModelProperty(value = "文章id/英文")
    private String articleId;

    /**
     * 文章名称
     */
    @TableField(value = "article_title", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "文章名称")
    private String articleTitle;

    /**
     * 发布时间
     */
    @TableField(value = "create_time", condition = SqlCondition.EQUAL, fill = FieldFill.INSERT)
    @ApiModelProperty(value = "发布时间")
    private Date createTime;
    /**
     * 文章简介
     */
    @TableField(value = "article_short", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "文章简介")
    private String articleShort;
    /**
     * 查看人数
     */
    @TableField(value = "views_count")
    @ApiModelProperty(value = "查看人数")
    private Integer viewsCount;
    /**
     * 评论人数
     */
    @TableField(value = "comments_count")
    @ApiModelProperty(value = "评论人数")
    private Integer commentsCount;
    /**
     * 是否置顶
     */
    @TableField(value = "is_top")
    @ApiModelProperty(value = "是否置顶")
    private Boolean isTop;
    /**
     * 文章图片url
     */
    @TableField(value = "img_url")
    @ApiModelProperty(value = "文章图片url")
    private String imgUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_ARTICLE_ID = "article_id";

    public static final String COL_ARTICLE_TITLE = "article_title";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_ARTICLE_SHORT = "article_short";

    public static final String COL_VIEWS_COUNT = "views_count";

    public static final String COL_COMMENTS_COUNT = "comments_count";

    public static final String COL_IS_TOP = "is_top";
}
