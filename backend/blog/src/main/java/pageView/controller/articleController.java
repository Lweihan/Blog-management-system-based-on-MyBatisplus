package pageView.controller;

import org.springframework.web.bind.annotation.*;
import pageView.domain.ResponseResult;
import pageView.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/article")
public class articleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        ResponseResult result = articleService.articleList(pageNum, pageSize, categoryId);
        return result;
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        ResponseResult result = articleService.getArticleDetail(id);
        return result;
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        ResponseResult result = articleService.updateViewCount(id);
        return result;
    }
}
