package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pageView.domain.ResponseResult;
import pageView.domain.dto.AddArticleDto;
import pageView.domain.entity.Article;
import pageView.service.ArticleService;

@RestController
@RequestMapping("/content/article")
public class articleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto addArticleDto) {
        return articleService.add(addArticleDto);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.list(pageNum, pageSize, title, summary);
    }

    @GetMapping("/{id}")
    public ResponseResult get(@PathVariable("id") Long id) {
        return articleService.get(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id) {
        return articleService.deleteArticle(id);
    }
}
