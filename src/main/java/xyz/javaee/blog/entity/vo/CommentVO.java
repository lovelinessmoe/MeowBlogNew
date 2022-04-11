package xyz.javaee.blog.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 返回给前端的评论
 *
 * @author loveliness
 */
@Data
public class CommentVO {

    /**
     * 评论ID
     */
    private String commentId;

    /**
     * 评论人
     */
    private String userId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 父id
     */
    private String pId;

}
