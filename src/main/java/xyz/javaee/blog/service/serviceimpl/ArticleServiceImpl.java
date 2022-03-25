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

import java.util.Objects;

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
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrUpdateArticle(ArticleDetailVO articleVO) {
        try {
            Article getArticle = articleMapper.selectById(articleVO.getArticleId());
            Article article = articleConverter.articleDetailVOToArticle(articleVO);
            ArticleDetail articleDetail = articleConverter.articleDetailVOToArticleDetail(articleVO);
            if (Objects.isNull(getArticle)) {
                articleMapper.insert(article);
                articleDetailMapper.insert(articleDetail);
            } else {
                articleMapper.updateById(article);
                articleDetailMapper.updateById(articleDetail);
            }
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

    @Override
    public ArticleDetailVO getArticleDetail(String articleId) {
        return articleMapper.getArticleDetail(articleId);
    }
}
