package xyz.javaee.blog.service.serviceimpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.blog.dao.RoleMapper;
import xyz.javaee.blog.entity.Role;
import xyz.javaee.blog.service.RoleService;
/**
 * @author loveliness
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

}
