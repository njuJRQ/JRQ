package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.CJKXNews;
import njurestaurant.njutakeout.entity.article.News;

import java.util.List;
import java.util.Optional;

public interface NewsDataService {
	void addCJKXNews(CJKXNews cjkxNews); //在向CJKXNews表中添加财经快讯的同时在News表中添加一条记录
	Optional<News> findNewsById(String newsId);
	Optional<CJKXNews> findCJKXNewsById(String id);
	List<News> getNewsListBeforeTimestamp(String type, long timestamp);

	void saveCJKXNews(CJKXNews cjkxNews); //只保存CJKXNews，不保存相关News
	void saveNews(News news); //只保存News，不保存相关来源数据
	void deleteCJKXNews(CJKXNews cjkxNews); //只删除CJKXNews，不删除相关News
	void deleteNews(News news); //只删除News，不删除相关来源的记录
}
