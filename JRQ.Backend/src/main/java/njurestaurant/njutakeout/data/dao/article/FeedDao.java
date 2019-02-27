package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, String> {
	List<Feed> findFeedsByWriterOpenidOrderByTimeStampDesc(String writerOpenid);
	List<Feed> findFeedsByWriterOpenidInAndTimeStamp(List<String> friendOpenids, long timeStamp);
	List<Feed> findTop10ByWriterOpenidInOrderByTimeStampDesc(List<String> friendOpenids);
	List<Feed> findTop10ByWriterOpenidInAndTimeStampBeforeOrderByTimeStampDesc(List<String> friendOpenids, long timeStamp);

	List<Feed> findTop10ByOrderByLikeNumDesc();
	List<Feed> findTop10ByLikeNumBeforeOrderByLikeNumDesc(long likeNum);
	List<Feed> findFeedsByLikeNum(long likeNum);

	List<Feed> findTop10ByOrderByTimeStampDesc();
	List<Feed> findTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
	List<Feed> findFeedsByTimeStamp(long timeStamp);

	List<Feed> findTopByIsPreferredOrderByTimeStampDesc(Boolean isPreferred);
	List<Feed> findTopByIsPreferredAndTimeStampBeforeOrderByTimeStampDesc(Boolean isPreferred, long timeStamp);

}
