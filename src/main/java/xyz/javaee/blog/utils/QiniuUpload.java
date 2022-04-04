package xyz.javaee.blog.utils;

import com.qiniu.util.Auth;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author loveliness
 * @description 七牛上传工具类
 */
@NoArgsConstructor
@Component
public class QiniuUpload {

    @Value("${qi-niu.access-key}")
    private String accessKey;
    @Value("${qi-niu.secret-key}")
    private String secretKey;
    @Value("${qi-niu.bucket}")
    private String bucketName;


    /**
     * 客户端获取上传的凭证
     *
     * @return 七牛上传凭证
     */
    public String getUpToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName);
    }
}
