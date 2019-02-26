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

	/**
	 * 按照均融优选分页查询
	 * @param openid 用户openid
	 * @param id 每页最后一条数据id,第一次为空字符串
	 * @return 项目列表
	 * @throws NotExistException
	 */
	List<Feed> getFeedListByIsPreferred(String openid,String id) throws NotExistException;
	List<Feed> getFeedListByIsPreferredDesc(String openid,long timeStamp) throws NotExistException;

	/**
	 * 按照热度排序分页查询
	 * @param openid 用户openid
	 * @param id 每页最后一条数据id,第一次为空字符串
	 * @return 项目列表
	 * @throws NotExistException
	 */
	List<Feed> getFeedListByLikeNum(String openid,String id) throws NotExistException;
	List<Feed> getFeedListByLikeNumDesc(String openid,long likeNum) throws NotExistException;

	/**
	 * 按照时间排序分页查询
	 * @param openid 用户openid
	 * @param id 每页最后一条数据id,第一次为空字符串
	 * @return 项目列表
	 * @throws NotExistException
	 */
	List<Feed> getFeedListByTimeStamp(String openid,String id) throws NotExistException;
	List<Feed> getFeedListByTimeStampDesc(String openid,long timeStamp) throws NotExistException;

}
