package xyz.javaee.blog.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.blog.constants.SecurityConstants;
import xyz.javaee.blog.entity.Comment;
import xyz.javaee.blog.entity.vo.CommentVO;
import xyz.javaee.blog.service.CommentService;
import xyz.javaee.blog.utils.JwtTokenUtils;
import xyz.javaee.blog.utils.Result;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/getComment/{articleId}")
    @ApiOperation(value = "获取文章的所有评论", notes = "传入articleId")
    public Result getCommentByArticleId(@ApiParam("文章id") @PathVariable String articleId) {
        return Result.ok().data(commentService.getCommentByArticleId(articleId));
    }

    @PostMapping("/addComment")
    @ApiOperation(value = "评论", notes = "传入评论")
    public Result addComment(@ApiParam("用户发表的评论") @RequestBody Comment comment,
                             @ApiParam("用户的token 用来获取用户id") @RequestHeader(SecurityConstants.TOKEN_HEADER) String token) {
        token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        String userId = JwtTokenUtils.getId(token);
        comment.setUserId(userId);
        boolean save = commentService.save(comment);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}
