package xyz.javaee.blog.dao;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.javaee.blog.entity.Comment;
import xyz.javaee.blog.entity.vo.CommentVO;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 获取文章评论
     *
     * @param articleId 文章id
     * @return 文章VOList
     */
    List<CommentVO> getCommentByArticleId(String articleId);

}
