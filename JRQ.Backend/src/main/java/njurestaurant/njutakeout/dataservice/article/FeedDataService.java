package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface FeedDataService {

	boolean isFeedExistent(String id);

	void saveFeed(Feed feed);

	void addFeed(Feed feed);

	Feed getFeedById(String id) throws NotExistException;

	List<Feed> getAllFeeds();

	List<Feed> getFeedsByWriterOpenidInAndTimeStamp(List<String> friendOpenids, long timeStamp);

	List<Feed> getTop10ByWriterOpenidInOrderByTimeStampDesc(List<String> friendOpenids);

	List<Feed> getTop10ByWriterOpenidInAndTimeStampBeforeOrderByTimeStampDesc(List<String> friendOpenids, long timeStamp);

	List<Feed> getFeedsByWriterOpenid(String writerOpenid);

	void deleteFeedById(String id) throws NotExistException;

	void deleteFeedsByWriterOpenid(String openid);
}
