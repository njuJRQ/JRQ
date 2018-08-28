package njurestaurant.njutakeout.blservice.article;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectResponse;

public interface ArticleBlService {

	/**
	 * 获取首页某一类别文章摘要列表
	 * @param kind 文章类别
	 * @return 文章摘要列表
	 */
	GetAbstractsResponse getAbstracts(String kind);

	/** TODO: 需要注明类别
	 * 获取某一文章详情
	 * @param id 文章ID
	 * @return 文章内容
	 */
	GetOneArticleResponse getOneArticleById(long id);

	/**
	 * 获取首页广告
	 * @return 首页广告信息
	 */
	GetAdResponse getAd();

	/** TODO: 需要注明类别
	 *  文章点赞
	 * @param id 文章ID
	 * @param openId 微信用户ID
	 * @return 点赞状态
	 */
	LikePlusResponse likePlus(long id, String openId);

}
