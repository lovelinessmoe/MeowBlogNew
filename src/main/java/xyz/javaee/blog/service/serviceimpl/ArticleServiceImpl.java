package xyz.javaee.blog.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import xyz.javaee.blog.dao.ArticleDetailMapper;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.dao.ArticleMapper;
import xyz.javaee.blog.entity.ArticleDetail;
import xyz.javaee.blog.entity.converter.ArticleConverter;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;
import xyz.javaee.blog.service.ArticleService;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleConverter articleConverter;
    private final ArticleMapper articleMapper;
    private final ArticleDetailMapper articleDetailMapper;

    @Override
    public ArticleDetail getOneArticle(String articleId) {
        return articleMapper.getOneArticle(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addArticle(ArticleDetailVO articleVO) {
        try {
            Article article = articleConverter.articleDetailVOToArticle(articleVO);
            articleMapper.insert(article);
            ArticleDetail articleDetail = articleConverter.articleDetailVOToArticleDetail(articleVO);
            articleDetailMapper.insert(articleDetail);
        } catch (Exception e) {
            return Result.RCode(false, ResultCode.ARTICLE_NOT_ADD);
        }
        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result removeArticle(String articleId) {
        try {
            articleMapper.deleteById(articleId);
            articleDetailMapper.deleteById(articleId);
        } catch (Exception e) {
            return Result.RCode(false, ResultCode.ARTICLE_NOT_DELET);
        }
        return Result.ok();
    }
}
