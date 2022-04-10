package xyz.javaee.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.javaee.blog.service.RoleService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lovleiness
 * @description JWT用户对象
 */
@Component
@Data
@AllArgsConstructor
public class JwtUser implements UserDetails {

    @JsonIgnore
    @Resource
    RoleService roleService;

    private String userId;
    private Integer roleId;
    private String userName;
    private String telephone;
    private Integer age;
    @JsonIgnore
    private String password;
    private String email;

    private String token;
    private Boolean enabled;
    private Integer role;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 通过 user 对象创建jwtUser
     */
    public JwtUser(User user) {
        this.userId = user.getUserId();
        this.roleId = user.getRoleId();
        this.userName = user.getUserName();
        this.telephone = user.getTelephone();
        this.age = user.getAge();
        this.password = user.getPassword();
        this.email = user.getEmail();

        enabled = true;
        role = user.getRoleId();
        authorities = this.getRoles();
    }

    private static JwtUser jwtUser;


    /**
     * 在非controller和service中注入service和mapper需要下注解
     */
    @PostConstruct
    public void init() {
        jwtUser = this;
        jwtUser.roleService = this.roleService;
    }

    public List<SimpleGrantedAuthority> getRoles() {
        Role roleById = jwtUser.roleService.getById(role);
        String role = roleById.getRoleNameEn();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
