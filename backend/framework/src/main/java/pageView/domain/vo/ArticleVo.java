package pageView.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {
    //所属分类id
    private Long categoryId;
    //文章内容
    private String content;

    private Date createTime;
    @TableId
    private Long id;

    private String isComment;

    private String isTop;

    private String status;

    private String summary;

    private String thumbnail;
    //标题
    private String title;
    //访问量
    private Long viewCount;
}
