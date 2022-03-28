package xyz.javaee.blog.service;

import xyz.javaee.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.blog.entity.ArticleDetail;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;
import xyz.javaee.blog.utils.Result;

import java.util.List;

/**
 * @author loveliness
 */
public interface ArticleService extends IService<Article> {
    /**
     * 如果存在则更新
     * 不存在则插入
     *
     * @param articleVO 博客文章的所有信息
     * @return 是否成功
     */
    Result saveOrUpdateArticle(ArticleDetailVO articleVO);

    /**
     * 删除博客文章
     *
     * @param articleId 文章id
     * @return 是否成功
     */
    Result removeArticle(String articleId);

    /**
     * 获取文章细节
     *
     * @param articleId 文章id
     * @return 文章对象的文章detail对象的相同
     */
    ArticleDetailVO getArticleDetail(String articleId);

    /**
     * 删除多个文章
     *
     * @param articleList 文章列表
     * @return Result
     */
    Result removeMany(List<Article> articleList);
}
