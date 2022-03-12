package xyz.javaee.blog.controller.backstage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.blog.entity.User;
import xyz.javaee.blog.service.UserService;
import xyz.javaee.blog.utils.Result;
import xyz.javaee.blog.utils.ResultCode;

import javax.validation.Valid;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/backstage/user")
public class UserController {

    private final UserService userService;


    /**
     * 新增或修改用户
     */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入用户")
    public Result submit(@Valid @RequestBody User user) {
        return Result.RCode(userService.saveOrUpdate(user), ResultCode.SUCCESS);
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "传入用户")
    public Result update(@Valid @RequestBody User user) {
        return Result.RCode(userService.updateById(user), ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除用户", notes = "传入ids")
    public Result remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return Result.RCode(userService.removeById(ids), ResultCode.SUCCESS);
    }

    /**
     * 分页 文章
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入用户")
    public Result list(User user, Page query) {
//        System.err.println(query.toString());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        IPage<User> pages = userService.page(query, userQueryWrapper);
        return Result.ok().data(pages);
    }

}
