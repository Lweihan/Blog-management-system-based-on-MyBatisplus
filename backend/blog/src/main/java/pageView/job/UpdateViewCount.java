package pageView.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pageView.domain.entity.Article;
import pageView.service.ArticleService;
import pageView.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCount {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount() {
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> articleList = viewCountMap.entrySet()
                .stream()
                        .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                                .collect(Collectors.toList());
        //更新到数据库中
        for (Article article : articleList) {
            articleService.updateViewCounttoDB(article.getId(), article.getViewCount());
        }

    }
}
