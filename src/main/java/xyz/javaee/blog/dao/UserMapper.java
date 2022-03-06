package xyz.javaee.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.javaee.blog.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Integer register(User userRegister);
}
