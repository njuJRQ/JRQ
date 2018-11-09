package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.CJKXNews;
import njurestaurant.njutakeout.entity.article.News;

import java.util.List;
import java.util.Optional;

public interface NewsDataService {
	void addCJKXNews(CJKXNews cjkxNews);
	Optional<News> findNewsById(String newsId);
	Optional<CJKXNews> findCJKXNewsById(String id);
	List<News> getNewsListBeforeTimestamp(String type, long timestamp);
}
