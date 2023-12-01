package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.ResponseResult;
import pageView.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author 软件2001李威翰
 * @since 2022-12-19 11:24:54
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Long articleId, Integer pageNumber, Integer pageSize);

    ResponseResult addComment(Comment comment);

    ResponseResult commentList(Integer pageNumber, Integer pageSize);
}

