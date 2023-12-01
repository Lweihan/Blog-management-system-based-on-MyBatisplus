package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.ResponseResult;
import pageView.domain.dto.AddArticleDto;
import pageView.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto addArticleDto);

    ResponseResult list(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult get(Long id);

    ResponseResult updateArticle(Article article);

    ResponseResult deleteArticle(Long id);

    void updateViewCounttoDB(Long id, Long viewCount);
}
