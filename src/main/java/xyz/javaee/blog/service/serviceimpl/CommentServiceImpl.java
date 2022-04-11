package xyz.javaee.blog.service.serviceimpl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.dao.CommentMapper;
import xyz.javaee.blog.entity.Comment;
import xyz.javaee.blog.entity.vo.CommentVO;
import xyz.javaee.blog.service.CommentService;
import xyz.javaee.blog.utils.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final CommentMapper commentMapper;


    @Override
    public List<Tree<String>> getCommentByArticleId(String articleId) {
        List<CommentVO> comments = commentMapper.getCommentByArticleId(articleId);

        //转换成树
        // 构建node列表

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名
        treeNodeConfig.setIdKey("commentId");
        treeNodeConfig.setParentIdKey("pId");
        treeNodeConfig.setNameKey("userName");
        // 最大递归深度
        treeNodeConfig.setDeep(2);

        //转换器
        return TreeUtil.build(comments, "0", treeNodeConfig,
                (comment, tree) -> {
                    tree.setId(comment.getCommentId());
                    tree.setParentId(comment.getPId());
                    tree.setName(comment.getUserName());
                    tree.putExtra("userId", comment.getUserId());
                    tree.putExtra("content", comment.getContent());
                    tree.putExtra("createTime", comment.getCreateTime());
                });
    }


    @Override
    public Result removeCommentById(String commentId) {
        UpdateWrapper<Comment> commentUpdateWrapper = new UpdateWrapper<>();
        commentUpdateWrapper.set(Comment.COL_CONTENT, "### 该评论已删除");
        commentUpdateWrapper.eq(Comment.COL_COMMENT_ID, commentId);
        commentMapper.update(null, commentUpdateWrapper);

        return Result.ok();
    }


    @Override
    public Result removeManyComment(ArrayList<Comment> commentList) {
        ArrayList<String> strings = new ArrayList<>();
        for (Comment comment : commentList) {
            strings.add(comment.getCommentId());
        }

        UpdateWrapper<Comment> commentUpdateWrapper = new UpdateWrapper<>();
        commentUpdateWrapper.set(Comment.COL_CONTENT, "### 该评论已删除");
        commentUpdateWrapper.in(Comment.COL_COMMENT_ID, strings);
        commentMapper.update(null, commentUpdateWrapper);
        return Result.ok();
    }
}
