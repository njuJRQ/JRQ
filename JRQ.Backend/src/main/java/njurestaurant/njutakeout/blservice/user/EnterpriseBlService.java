package njurestaurant.njutakeout.blservice.user;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.user.EnterpriseListResponse;
import njurestaurant.njutakeout.response.user.EnterpriseResponse;

public interface EnterpriseBlService {

	/**
	 * 根据ID获取特定企业信息(Admin)
	 * @param id 企业ID
	 * @return 企业信息
	 */
	EnterpriseResponse getEnterpriseById(String id) throws NotExistException;

	/**
	 * 超级管理员获取所有企业信息(Admin)
	 * @return 所有企业信息
	 */
	EnterpriseListResponse getAllEnterprises();

	/**
	 * 超级管理员将企业申请设置为通过审核(Admin)
	 * @param id 企业ID
	 * @return 是否操作成功
	 */
	BoolResponse verifyEnterpriseById(String id) throws NotExistException;

	/**
	 * 超级管理员删除企业(Admin)
	 * 原先状态为"submitted"的表项将变为"rejected"，原先状态为"verified"的表项将变为"disqualified"
	 * 在用户调用getMySubmittedEnterprise之后这个表项才会被删除
	 * @param id 企业ID
	 * @return 操作是否成功
	 */
	InfoResponse deleteEnterpriseById(String id) throws NotExistException;

	/**
	 * 用户升级自己为企业账户
	 * @param enterpriseName 企业名称
	 * @param description 企业描述（由前端限制描述不应少于30字）
	 * @param licenseUrl 企业经营许可证URL
	 * @param openid 用户的微信openid
	 * @param username 企业用户管理员的username（之后会作为文章的writerName）
	 * @param password 企业用户管理员的密码
	 * @return 成功则返回用户名密码信息；失败则返回错误信息
	 */
	BoolResponse setMyUserAsEnterprise(String enterpriseName, String description, String licenseUrl, String openid, String username, String password);

	/**
	 * 用户获取自己是否为企业用户
	 * @param openid 用户的openid
	 * @return 是否为企业用户
	 */
	BoolResponse isMyUserEnterprise(String openid);

	/**
	 * 用户获取自己已提交的企业用户信息
	 * 若用户未提交过，则返回空对象
	 * 若已提交的Enterprise状态为"submitted"(已提交申请)或"verified"(已通过申请)，正常返回
	 * 若已提交的Enterprise状态为"rejected"(申请被拒绝)或"disqualified"(被取消了资格)，返回信息之后相应对象会被删除
	 * @param openid 用户的openid
	 * @return 用户Enterprise信息
	 */
	EnterpriseResponse getMySubmittedEnterprise(String openid) throws NotExistException;

	/**
	 * 企业用户获取自己的管理员信息
	 * @param openid 企业用户的openid
	 * @return 管理员信息
	 */
	AdminResponse getMyEnterpriseAdmin(String openid) throws NotExistException;

	/**
	 * 检查管理员是否为企业用户
	 * @param adminId 管理员ID
	 * @return 是否为企业用户
	 */
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
