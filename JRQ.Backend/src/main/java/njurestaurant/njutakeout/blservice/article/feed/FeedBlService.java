package njurestaurant.njutakeout.blservice.article.feed;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.AbstractListResponse;
import njurestaurant.njutakeout.response.article.feed.FeedListResponse;
import njurestaurant.njutakeout.response.article.feed.FeedResponse;
import njurestaurant.njutakeout.response.article.feed.FeedViewListResponse;
import njurestaurant.njutakeout.response.article.feed.FeedViewResponse;

import java.util.List;

public interface FeedBlService {
	/**
	 * 用户发布自己的圈子文章(User)
	 * @param content 文章内容
	 * @param images 文章图片
	 * @param writerOpenid 作者微信openid
	 * @return 是否成功
	 */
	InfoResponse publishMyFeed(String content, List<String> images, String writerOpenid);

	/**
	 * 根据圈子文章ID获取全文(User)
	 * @param id 圈子文章ID
	 * @return 圈子文章信息
	 */
	FeedResponse getFeed(String id) throws NotExistException;

	/**
	 * 根据圈子文章ID获取含作者名字和头像的全文(User)
	 * @param id 圈子文章ID
	 * @return 圈子文章信息
	 */
	FeedViewResponse getFeedView(String id) throws NotExistException;

	/**
	 * 获取圈子全部文章信息(User)
	 * @return 圈子文章信息
	 */
	FeedListResponse getFeedList();

	/**
	 * 获取圈子全部含作者名字和头像的文章信息(User)
	 * @return 圈子文章信息
	 */
	FeedViewListResponse getFeedViewList() throws NotExistException;

	/**
	 * 用户获取自己发布过的文章摘要(User)
	 * @param openid 用户的微信openid
	 * @return 文章摘要
	 */
	AbstractListResponse getMyHistoryAbstractList(String openid) throws NotExistException;

	/**
	 * 用户更新自己发布的圈子文章信息(User)
	 * @param id 圈子文章ID
	 * @param content 新的文章内容
	 * @param images 新的图片
	 * @return 是否成功
	 */
	InfoResponse updateMyFeed(String id, String content, List<String> images) throws NotExistException;

	/**
	 * 用户根据圈子文章ID删除自己的圈子文章(User)
	 * @param id 圈子文章ID
	 * @return 是否成功
	 */
	InfoResponse deleteMyFeed(String id) throws NotExistException;
}
