package xyz.javaee.blog.entity.converter;


import org.mapstruct.Mapper;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.entity.vo.UserVO;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * 类型转换
     *
     * @param user user对象
     * @return userVO对象
     */
    UserVO userToVO(User user);

}
