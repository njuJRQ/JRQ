package njurestaurant.njutakeout.blservice.article;

import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;
import njurestaurant.njutakeout.response.article.ad.AdResponse;


public interface ArticleBlService {

	/**
	 * 获取摘要列表：包括首页和圈子(User&Admin)
	 * @param kind 文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子
	 * @param openid 只有在文章类型为feed时才会用到
	 * @return 摘要列表
	 */
	AbstractListResponse getAbstractList(String kind, String openid) throws NotExistException;

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
