package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, String> {
	List<Feed> findFeedsByWriterOpenid(String writerOpenid);
}
