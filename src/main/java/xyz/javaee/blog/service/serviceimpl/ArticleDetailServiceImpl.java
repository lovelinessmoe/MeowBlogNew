package xyz.javaee.blog.service.serviceimpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.dao.ArticleDetailMapper;
import xyz.javaee.blog.entity.ArticleDetail;
import xyz.javaee.blog.service.ArticleDetailService;

/**
 * @author loveliness
 */
@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail> implements ArticleDetailService {

}
