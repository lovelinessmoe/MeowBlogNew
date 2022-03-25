package xyz.javaee.blog.controller.backstage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/backstage/article")
public class ArticleController {

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
     * 分页 文章
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入文章")
    public Result list(Article article, PageDTO<Article> query) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>(article);
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
}
