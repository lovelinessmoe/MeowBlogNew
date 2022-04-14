package xyz.javaee.blog.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.javaee.blog.utils.QiniuUpload;
import xyz.javaee.blog.utils.Result;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/qiniu")
@Api(tags = "七牛")
@AllArgsConstructor
public class QiNiuController {

    private final QiniuUpload qiniuUpload;

    @GetMapping("/getQiNiuToken")
    public Result getQiNiuToken() {
        return Result.ok().data(qiniuUpload.getUpToken());
    }

}
