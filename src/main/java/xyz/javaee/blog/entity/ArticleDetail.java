package xyz.javaee.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loveliness
 */
@ApiModel(value="xyz-javaee-blog-entity-ArticleDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Meow.article_detail")
public class ArticleDetail implements Serializable {
    /**
     * 文章的id
     */
    @TableId(value = "article_id", type = IdType.INPUT)
    @ApiModelProperty(value="文章id/英文")
    private String articleId;

    /**
     * 文章的内容
     */
    @TableField(value = "article_content")
    @ApiModelProperty(value="文章的内容")
    private String articleContent;

    /**
     * 0h5 1md
     */
    @TableField(value = "type")
    @ApiModelProperty(value="0h5 1md")
    private Boolean type;

    private static final long serialVersionUID = 1L;

    public static final String COL_ARTICLE_ID = "article_id";

    public static final String COL_ARTICLE_CONTENT = "article_content";

    public static final String COL_TYPE = "type";
}
