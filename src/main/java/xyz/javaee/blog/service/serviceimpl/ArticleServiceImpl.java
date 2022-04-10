package xyz.javaee.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.javaee.blog.dao.ArticleDetailMapper;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.dao.ArticleMapper;
import xyz.javaee.blog.entity.ArticleDetail;
import xyz.javaee.blog.entity.converter.ArticleConverter;
import xyz.javaee.blog.entity.vo.ArticleDetailVO;
import xyz.javaee.blog.service.ArticleService;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;

import java.util.List;

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
            //查询是否存在改文章
            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>().eq(Article.COL_ARTICLE_ID, articleVO.getArticleId());
            Long count = articleMapper.selectCount(queryWrapper);
            //VO转换为实体
            Article article = articleConverter.articleDetailVOToArticle(articleVO);
            ArticleDetail articleDetail = articleConverter.articleDetailVOToArticleDetail(articleVO);

            if (count == 0) {
                articleMapper.insert(article);
                articleDetailMapper.insert(articleDetail);
            } else {
                articleMapper.updateById(article);
                articleDetailMapper.updateById(articleDetail);
            }
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.RCode(false, ResultCode.ARTICLE_NOT);
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
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.RCode(false, ResultCode.ARTICLE_NOT_DELET);
        }
        return Result.ok();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result removeMany(List<Article> articleList) {
        try {
            articleMapper.deleteBatchIds(articleList);
            articleDetailMapper.deleteBatchIds(articleList);
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
