package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, String> {
	List<Feed> findFeedsByWriterOpenid(String writerOpenid);
	List<Feed> findFeedsByWriterOpenidInAndTimeStamp(List<String> friendOpenids, long timeStamp);
	List<Feed> findTop10ByWriterOpenidInOrderByTimeStampDesc(List<String> friendOpenids);
	List<Feed> findTop10ByWriterOpenidInAndTimeStampBeforeOrderByTimeStampDesc(List<String> friendOpenids, long timeStamp);
	List<Feed> findTop10ByOrderByLikeNumDesc();
	List<Feed> findTop10ByLikeNumBeforeOrderByLikeNumDesc(long likeNum);
	List<Feed> findFeedsByLikeNum(long likeNum);
	List<Feed> findTop10ByTimeStampAfterOrderByTimeStampDesc(long timeStamp);
	List<Feed> findTop10ByTimeStampBetweenOrderByTimeStampDesc(long timeStamp1,long timeStamp2);
	List<Feed> findFeedsByTimeStamp(long timeStamp);
}
