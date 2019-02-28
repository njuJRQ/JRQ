package njurestaurant.njutakeout.blservice.article.feed;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.AbstractListResponse;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.feed.FeedListResponse;
import njurestaurant.njutakeout.response.article.feed.FeedResponse;
import njurestaurant.njutakeout.response.article.feed.FeedViewListResponse;
import njurestaurant.njutakeout.response.article.feed.FeedViewResponse;

import java.util.List;

public interface FeedBlService {
	/**
	 * 用户发布自己的圈子文章(User)
	 * @param title 文章标题
	 * @param content 文章内容
	 * @param images 文章图片
	 * @param writerOpenid 作者微信openid
	 * @return 是否成功
	 */
	InfoResponse publishMyFeed(String title, String content, List<String> images, String writerOpenid);

	/**
	 * 根据圈子文章ID获取全文(User&Admin)
	 * @param id 圈子文章ID
	 * @return 圈子文章信息
	 */
	FeedResponse getFeed(String id) throws NotExistException;

	/**
	 * 获取圈子全部文章信息(User&Admin)
	 * @return 圈子文章信息
	 */
	FeedListResponse getFeedList();

	/**
	 * 管理员更新圈子文章内容(Admin)
	 * @param id  圈子ID
	 * @param content  圈子文本
	 * @param images  圈子图片
	 * @return 是否修改成功
	 */
	InfoResponse updateFeed(String id, String content, List<String> images) throws NotExistException;

	/**
	 *管理员根据圈子ID删除圈子文章(Admin)
	 * @param id 圈子ID
	 * @return 是否成功
	 */
	InfoResponse deleteFeed(String id) throws NotExistException;

	/**
	 * 根据圈子文章ID获取含作者名字和头像的全文(User)
	 * @param id 圈子文章ID
	 * @return 圈子文章信息
	 */
	FeedViewResponse getFeedView(String id) throws NotExistException;

	/**此API已弃用
	 * 获取圈子全部含作者名字和头像的文章信息(User)
	 * @return 圈子文章信息
	 */
	FeedViewListResponse getFeedViewList() throws NotExistException;

	/**
	 * 获取某一篇圈子文章时间戳前的10篇文章
	 * 文章列表按照新旧排序，最新的在最前面，最旧的在最后面，如果有时间戳完全相同的，则不管10篇的限制，全部加入列表中
	 * @param id 圈子文章ID
	 * @return 圈子文章信息列表
	 */
	FeedViewListResponse getFeedViewListBefore(String openid, String id) throws NotExistException;

	/**
	 * 用户获取别人历史发布的文章摘要(User)
	 * @param myOpenid 用户的微信openid
	 * @param otherOpenid 要查看的人的微信openid
	 * @return 文章摘要
	 */
	AbstractListResponse getUserHistoryAbstractList(String myOpenid, String otherOpenid) throws NotExistException;

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


	/**
	 * 获取10条圈子按条件查询
	 * @param kind 条件查询，可能值：钧融优选isPreferred，热度hot，时间time
	 * @param openid 用户openid
	 * @param id 圈子ID
	 * @return 圈子文章信息列表
	 */
	FeedViewListResponse getFeedListBeforeByKind(String kind, String openid, String id) throws NotExistException;

	/**
	 * 通过标题模糊搜索
	 * @param openid 用户openid
	 * @param condition 搜索条件
	 * @return 圈子文章信息列表
	 * @throws NotExistException
	 */
	FeedViewListResponse getFeedListByCondition(String openid, String condition) throws NotExistException;

	/**
	 * 获取我发布的项目列表
	 * @param openid 用户openid
	 * @return 圈子文章信息列表
	 * @throws NotExistException
	 */
	FeedListResponse getMyFeedList(String openid) throws NotExistException;
}
