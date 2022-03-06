package xyz.javaee.blog.service.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.entity.Article;
import xyz.javaee.blog.dao.ArticleMapper;
import xyz.javaee.blog.service.ArticleService;
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

}
