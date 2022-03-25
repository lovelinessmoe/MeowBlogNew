package xyz.javaee.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;

/**
 * @author loveliness
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 通过id获取文章
     *
     * @param articleId 文章ID
     * @return 文章对象的文章detail对象的相同
     */
    ArticleDetailVO getArticleDetail(String articleId);
}
