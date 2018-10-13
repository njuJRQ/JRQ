package njurestaurant.njutakeout.blservice.article;

import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;
import njurestaurant.njutakeout.response.article.ad.AdResponse;


public interface ArticleBlService {

	/**
	 * 获取摘要列表：包括首页和圈子(User)
	 * @param kind 文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子
	 * @param openid 用户openid
	 * @return 摘要列表（包括用户是否点赞）
	 */
	AbstractListResponse getAbstractList(String kind, String openid) throws NotExistException;

	/**
	 * 获取摘要列表：仅包括首页不包括圈子，一次加载10条(User)
	 * @param kind 文章类型，可能值：course，document，project分别对应课程，文档，项目
	 * @param openid 用户openid
	 * @param articleId 文章ID
	 * @param articleType 参数中文章ID的文章类型
	 * @return 摘要列表（包括用户是否点赞）
	 */
	AbstractListResponse getAbstractListBefore(String kind, String openid, String articleId, String articleType) throws NotExistException;

	/**
	 * 根据条件搜索文章摘要列表：包括课程，项目，文档
	 * @param openid 用户的openid
	 * @param condition 搜索条件，目前是根据文章标题进行搜索
	 * @return 摘要列表
	 */
	AbstractListResponse getAbstractListByCondition(String openid, String condition);

	/**
	 * 获取文章详情(User&Admin)
	 * @param kind 文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子
	 * @param id 文章ID
	 * @return 文章详情
	 */
	ArticleResponse getArticle(String kind, String id) throws NotExistException;

	/**
	 * 点赞，若已经赞过则取消赞
	 * @param openid 用户微信ID
	 * @param kind 文章类型
	 * @param articleId 文章ID
	 * @return 是否成功
	 */
	InfoResponse likePlus(String openid, String kind, String articleId) throws NotExistException, AlreadyExistException;

}
