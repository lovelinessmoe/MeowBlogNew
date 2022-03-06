package xyz.javaee.blog.service.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.dao.CommentMapper;
import xyz.javaee.blog.entity.Comment;
import xyz.javaee.blog.service.CommentService;
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

}
