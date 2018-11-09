package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsDao extends JpaRepository<News, String> {
	List<News> findNewsByTimestamp(long timestamp);
	List<News> findTop10ByOrderByTimestampDesc();
	List<News> findTop50ByOrderByTimestampDesc();
	List<News> findTop10ByTimestampBeforeOrderByTimestampDesc(long timestamp);
	List<News> findTop50ByTimestampBeforeOrderByTimestampDesc(long timestamp);
}
