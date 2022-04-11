package xyz.javaee.blog.service;

import cn.hutool.core.lang.tree.Tree;
import xyz.javaee.blog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.blog.utils.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loveliness
 */
public interface CommentService extends IService<Comment> {


    /**
     * 通过文章id返回评论
     *
     * @param articleId 文章id
     * @return 评论列表
     */
    List<Tree<String>> getCommentByArticleId(String articleId);

    /**
     * 更新评论为已删除
     *
     * @param commentId 评论id
     * @return Result
     */
    Result removeCommentById(String commentId);

    /**
     * 批量更新为已删除
     *
     * @param commentList 评论list
     * @return Result
     */
    Result removeManyComment(ArrayList<Comment> commentList);
}
