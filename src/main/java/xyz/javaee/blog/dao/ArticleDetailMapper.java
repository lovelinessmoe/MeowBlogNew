package xyz.javaee.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.javaee.blog.entity.ArticleDetail;

@Mapper
public interface ArticleDetailMapper extends BaseMapper<ArticleDetail> {
}