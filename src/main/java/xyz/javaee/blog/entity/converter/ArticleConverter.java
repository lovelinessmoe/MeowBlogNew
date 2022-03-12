package xyz.javaee.blog.entity.converter;

import org.mapstruct.Mapper;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.entity.ArticleDetail;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface ArticleConverter {
    /**
     * 前端的添加文章转换成Article
     * @param ArticleDetailVO
     * @return Article
     */
    Article articleDetailVOToArticle(ArticleDetailVO ArticleDetailVO);

    /**
     * ArticleDetail
     * @param ArticleDetailVO
     * @return ArticleDetail
     */
    ArticleDetail articleDetailVOToArticleDetail(ArticleDetailVO ArticleDetailVO);
}

