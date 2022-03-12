package xyz.javaee.blog.service;

import xyz.javaee.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.blog.entity.ArticleDetail;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;
import xyz.javaee.blog.utils.Result;

/**
 * @author loveliness
 */
public interface ArticleService extends IService<Article>{

    /**
     * 获取一篇文章的详细信息
     * @param articleId 文章的ID
     * @return 这片文章的详细信息
     */
    ArticleDetail getOneArticle(String articleId);

    Result addArticle(ArticleDetailVO articleVO);

    Result removeArticle(String articleId);
}
