package xyz.javaee.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.javaee.blog.service.UpYunServer;
import xyz.javaee.blog.utils.Result;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/upYun")
@Api(tags = "又拍")
@AllArgsConstructor
public class UpYunController {

    private final UpYunServer upYunServer;

    @PostMapping("/uploadFile")
    @ApiOperation("上传文件")
    public Result getToken(@ApiParam("文件路径前缀") @RequestPart String uri,
                           @ApiParam("文件") @RequestPart MultipartFile file) {
        Response response = upYunServer.uploadFile(uri, file);
        int successCode = 200;
        String baseImgUrl = "https://img.javaee.xyz";
        if (response.code() == successCode) {
            return Result.ok().message("上传成功").data(baseImgUrl + uri);
        } else {
            return Result.error().message("上传失败");
        }
    }
}
