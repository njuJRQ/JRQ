package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Course;
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


	List<Feed> getFeedListByLikeNum(String openid,String id) throws NotExistException;
	List<Feed> getFeedListByLikeNumDesc(String openid,long likeNum) throws NotExistException;

	List<Feed> getFeedListBeforeWeek(String openid,String id) throws NotExistException;
	List<Feed> getFeedListBeforeWeekDesc(String openid,long timeStamp) throws NotExistException;

	List<Feed> getFeedListBeforeMonth(String openid,String id) throws NotExistException;
	List<Feed> getFeedListBeforeMonthDesc(String openid,long timeStamp) throws NotExistException;
}
