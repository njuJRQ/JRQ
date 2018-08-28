package njurestaurant.njutakeout.blservice.article;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;

public interface ArticleBlService {

	/**
	 * 获取摘要列表：包括首页和圈子
	 * @param kind 文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子
	 * @param openid 只有在文章类型为feed时才会用到
	 * @return 摘要列表
	 */
	AbstractListResponse getAbstractList(String kind, String openid);

	/**
	 * 获取文章详情
	 * @param kind 文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子
	 * @param id 文章ID
	 * @return 文章详情
	 */
	ArticleResponse getArticle(String kind, long id);

	/**
	 * 获取首页广告
	 * @return 首页广告信息
	 */
	AdResponse getAd();

	/**
	 * 点赞
	 * @param kind 文章类型
	 * @param articleId 文章ID
	 * @param openid 用户微信ID
	 * @return 是否成功
	 */
	InfoResponse likePlus(String kind, long articleId, String openid);

}
