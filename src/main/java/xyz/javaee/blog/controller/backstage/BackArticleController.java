package xyz.javaee.blog.controller.backstage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;
import xyz.javaee.blog.service.ArticleService;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@Api(tags = "后台文章管理")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/backstage/article")
public class BackArticleController {

    private final ArticleService articleService;

    /**
     * 详情
     */
    @GetMapping("/detail/{articleId}")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入articleId")
    public Result detail(@PathVariable("articleId") String articleId) {
        ArticleDetailVO articleDetail = articleService.getArticleDetail(articleId);
        return Result.ok().data(articleDetail);
    }


    /**
     * 新增或修改文章
     */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入文章")
    public Result submit(@Valid @RequestBody Article article) {
        return Result.RCode(articleService.saveOrUpdate(article), ResultCode.SUCCESS);
    }

    /**
     * 修改文章
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "传入文章")
    public Result update(@Valid @RequestBody Article article) {
        return Result.RCode(articleService.updateById(article), ResultCode.SUCCESS);
    }

    /**
     * 删除文章
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除文章", notes = "传入id")
    public Result remove(@ApiParam(value = "主键", required = true) @RequestParam String articleId) {
        return articleService.removeArticle(articleId);
    }

    /**
     * 删除多个文章
     */
    @PostMapping("/removeMany")
    @ApiOperation(value = "删除多个文章", notes = "传入多个对象")
    public Result removeMany(@ApiParam(value = "对象List", required = true)
                             @RequestBody ArrayList<Article> articleList) {
        return articleService.removeMany(articleList);
    }

    /**
     * 分页 文章
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入文章")
    public Result list(Article article, PageDTO<Article> query) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>(article);
        articleQueryWrapper.orderByDesc(Article.COL_CREATE_TIME);
        PageDTO<Article> pages = articleService.page(query, articleQueryWrapper);
        return Result.ok().data(pages);
    }

    /**
     * 添加 文章
     */
    @PostMapping("/saveOrUpdateArticle")
    @ApiOperation(value = "添加或更新文章", notes = "传入文章")
    public Result saveOrUpdateArticle(@RequestBody ArticleDetailVO articleVO) {
        return articleService.saveOrUpdateArticle(articleVO);
    }

    /**
     * 更换置顶状态
     */
    @PostMapping("/switchTop")
    @ApiOperation(value = "更换置顶状态", notes = "传入文章id")
    public Result switchTop(@RequestParam String articleId) {
        UpdateWrapper<Article> articleUpdateWrapper = new UpdateWrapper<>();
        //isTop取反
        articleUpdateWrapper.setSql(Article.COL_IS_TOP + "= NOT " + Article.COL_IS_TOP);
        //限定文章
        articleUpdateWrapper.eq(Article.COL_ARTICLE_ID, articleId);
        //更新
        return Result.ok().data(articleService.update(articleUpdateWrapper));
    }
}
