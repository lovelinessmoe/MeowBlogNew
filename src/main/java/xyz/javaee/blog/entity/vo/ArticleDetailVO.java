package xyz.javaee.blog.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVO {
    /**
     * 文章的id
     */
    private String articleId;

    /**
     * 文章的内容
     */
    private String articleContent;

    /**
     * 文章名称
     */
    private String articleTitle;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 博客简介
     */
    private String articleShort;

    /**
     * 查看人数
     */
    private Integer viewsCount;

    /**
     * 评论人数
     */
    private Integer commentsCount;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 文章图片url
     */
    private String imgUrl;
}
