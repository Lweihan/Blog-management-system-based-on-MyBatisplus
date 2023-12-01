package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pageView.domain.ResponseResult;
import pageView.domain.entity.Comment;
import pageView.service.CommentService;

@RestController
@RequestMapping("/comment")
public class commentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNumber, Integer pageSize) {
        ResponseResult result = commentService.commentList(articleId, pageNumber, pageSize);
        return result;
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment) {
        ResponseResult result = commentService.addComment(comment);
        return result;
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNumber, Integer pageSize) {
        ResponseResult result = commentService.commentList(pageNumber, pageSize);
        return result;
    }
}
