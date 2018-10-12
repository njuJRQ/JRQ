package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, String> {
	List<Feed> findFeedsByWriterOpenid(String writerOpenid);
	List<Feed> findFeedsByTimeStamp(long timeStamp);
	List<Feed> findTop10ByOrderByTimeStampDesc();
	List<Feed> findFeedsByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
	List<Feed> findTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
//	@Query("select f from Feed f where f.timeStamp < ?1 order by f.timeStamp desc")
//	List<Feed> findFeedsByTimeStampBeforeOrderByTimeStampDesc(long timeStamp, Pageable pageable);
}
