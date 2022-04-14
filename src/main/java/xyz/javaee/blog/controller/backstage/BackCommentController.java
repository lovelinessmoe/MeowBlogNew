package xyz.javaee.blog.controller.backstage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.blog.entity.Comment;
import xyz.javaee.blog.service.CommentService;
import xyz.javaee.blog.utils.Result;

import java.util.ArrayList;


/**
 * @author loveliness
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@Api(tags = "后台评论管理")
@RequestMapping("/backstage/comment")
@AllArgsConstructor
public class BackCommentController {

    private final CommentService commentService;

    @GetMapping("/getComment/{articleId}")
    @ApiOperation(value = "获取文章的所有评论", notes = "传入articleId")
    public Result getCommentByArticleId(@ApiParam("文章id") @PathVariable String articleId) {
        return Result.ok().data(commentService.getCommentByArticleId(articleId));
    }

    @GetMapping("/remove/{commentId}")
    @ApiOperation(value = "删除文章的评论", notes = "传入commentId")
    public Result remove(@ApiParam("评论id") @PathVariable String commentId) {
        return Result.ok().data(commentService.removeCommentById(commentId));
    }


    /**
     * 删除多条评论
     */
    @PostMapping("/removeMany")
    @ApiOperation(value = "删除多条评论", notes = "传入评论集合")
    public Result removeMany(@ApiParam(value = "用户集合", required = true)
                             @RequestBody ArrayList<Comment> commentList) {
        return commentService.removeManyComment(commentList);
    }
}
