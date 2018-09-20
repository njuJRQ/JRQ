package njurestaurant.njutakeout.blservice.user;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;

public interface EnterpriseBlService {
	/**
	 * 用户升级自己为企业账户
	 * @param openid 用户的微信openid
	 * @return 成功则返回用户名密码信息；失败则返回错误信息
	 */
	BoolResponse setMyUserAsEnterprise(String openid);

	BoolResponse isAdminEnterprise(String adminId);

	/**
	 * 企业用户管理员获取自己发布的课程列表
	 * @param adminId 管理员ID
	 * @return 课程列表
	 */
	CourseListResponse getMyPublishedCourseList(String adminId) throws NotExistException;

	/**
	 * 企业用户管理员获取自己发布的文档列表
	 * @param adminId 管理员ID
	 * @return 文档列表
	 */
	DocumentListResponse getMyPublishedDocumentList(String adminId) throws NotExistException;

	/**
	 * 企业用户管理员获取自己发布的项目列表
	 * @param adminId 管理员ID
	 * @return 项目列表
	 */
	ProjectListResponse getMyPublishedProjectList(String adminId) throws NotExistException;
}
