package xyz.javaee.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.entity.ArticleDetail;

/**
 * @author loveliness
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    ArticleDetail getOneArticle(String articleId);

}
