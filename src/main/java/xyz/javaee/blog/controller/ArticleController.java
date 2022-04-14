package xyz.javaee.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;
import xyz.javaee.blog.service.ArticleService;
import xyz.javaee.blog.utils.Result;

/**
 * @author loveliness
 */
@RestController
@Api(tags = "前台文章")
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/getList")
    @ApiOperation(value = "获取列表", notes = "传入articleId")
    public Result getList(Article article, PageDTO<Article> query) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>(article);
        articleQueryWrapper.orderByDesc(Article.COL_IS_TOP);
        articleQueryWrapper.orderByDesc(Article.COL_VIEWS_COUNT);
        articleQueryWrapper.orderByDesc(Article.COL_COMMENTS_COUNT);
        articleQueryWrapper.orderByDesc(Article.COL_CREATE_TIME);
        PageDTO<Article> pages = articleService.page(query, articleQueryWrapper);

        return Result.ok()
                //文章列表
                .data("records", pages.getRecords())
                //是否有下一页
                .data("hasNextPage", pages.hasNext());
    }


    /**
     * 详情
     */
    @GetMapping("/detail/{articleId}")
    @ApiOperation(value = "详情", notes = "传入articleId")
    public Result detail(@PathVariable("articleId") String articleId) {
        UpdateWrapper<Article> articleUpdateWrapper = new UpdateWrapper<>();
        //更新人数自增
        articleUpdateWrapper.setSql(Article.COL_VIEWS_COUNT + "=" + Article.COL_VIEWS_COUNT + "+1");
        //限定文章
        articleUpdateWrapper.eq(Article.COL_ARTICLE_ID, articleId);
        //更新
        articleService.update(articleUpdateWrapper);
        //获取详细
        ArticleDetailVO articleDetail = articleService.getArticleDetail(articleId);
        return Result.ok().data(articleDetail);
    }
}
